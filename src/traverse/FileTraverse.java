package traverse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileTraverse {

    private final boolean processSubDirectories;

    public FileTraverse(boolean processSubDirectories) {
        this.processSubDirectories = processSubDirectories;
    }

    public List<FileDataDto> traverse(String path) throws IOException {
        try (Stream<Path> paths = Files.walk(Path.of(path), processSubDirectories ? Integer.MAX_VALUE : 1)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(this::processSingleFile)
                    .toList();
        }
    }

    private FileDataDto processSingleFile(File file) {
        FileDto fileDto = new FileDto(file);
        FileContentVerifier verifier = new FileContentVerifier(file);
        try {
            verifier.verify();
            fileDto.setDangerRateAndLevel(verifier);
            fileDto.setVerifierMessage(verifier.createMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDto.getFileData();
    }
}