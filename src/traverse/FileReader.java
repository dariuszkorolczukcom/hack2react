package traverse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<FileDataDto> traverse(String path) throws IOException {
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        List<FileDataDto> data = new ArrayList<>();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                _File file = new _File(child);
                FileContentVerifier verifier = new FileContentVerifier(child);
                verifier.verify();
                file.setDangerLevel(verifier.calculateDangerLevel());
                file.setVerifierMessage(verifier.createMessage());
                data.add(file.getFileData());
            }
        }
        return data;
    }
}
