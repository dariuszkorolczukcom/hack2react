package traverse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTraverse {

    private final boolean processSubDirectories;

    public FileTraverse(boolean processSubDirectories) {
        this.processSubDirectories = processSubDirectories;
    }

    public List<FileDataDto> traverse(String path) throws IOException {
        File dir = new File(path);
        List<FileDataDto> data = new ArrayList<>();
        if (dir.isDirectory()) {
            data = processFiles(dir.listFiles());
        } else if (dir.isFile()) {
            data.add(processSingleFile(dir));
        }
        return data;
    }

    private List<FileDataDto> processFiles(File[] files) throws IOException {
        List<FileDataDto> fileList = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory() && processSubDirectories) {
                fileList.addAll(processFiles(file.listFiles()));
            } else if (file.isFile()) {
                fileList.add(processSingleFile(file));
            }
        }
        return fileList;
    }

    private FileDataDto processSingleFile(File file) throws IOException {
        FileDto fileDto = new FileDto(file);
        FileContentVerifier verifier = new FileContentVerifier(file);
        verifier.verify();
        int dangerLevel = verifier.calculateDangerRate();
        fileDto.setDangerRate(dangerLevel);
        fileDto.setDangerLevel(verifier.calculateDangerLevel(dangerLevel));
        fileDto.setVerifierMessage(verifier.createMessage());
        return fileDto.getFileData();
    }
}