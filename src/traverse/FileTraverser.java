package traverse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTraverser {

    public List<FileDataDto> traverse(String path) throws IOException {
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        List<FileDataDto> data = new ArrayList<>();
        if (directoryListing != null) {
            data = processFiles(directoryListing);
        }
        return data;
    }

    private List<FileDataDto> processFiles(File[] files) throws IOException {
        List<FileDataDto> fileList = new ArrayList<>();
        for (File file : files) {
            fileList.add(processSingleFile(file));
        }
        return fileList;
    }

    private FileDataDto processSingleFile(File file) throws IOException {
        _File _file = new _File(file);
        FileContentVerifier verifier = new FileContentVerifier(file);
        verifier.verify();
        _file.setDangerLevel(verifier.calculateDangerLevel());
        _file.setVerifierMessage(verifier.createMessage());
        return _file.getFileData();
    }
}