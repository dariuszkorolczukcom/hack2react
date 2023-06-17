package traverse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileDto {
    private final Path path;
    private String created;
    private String modified;
    private DangerLevel dangerLevel;
    private int dangerRate;
    private String verifierMessage;

    public FileDto(File file) {
        this.path = Path.of(file.getPath());
        try {
            BasicFileAttributes attr = Files.readAttributes(this.path, BasicFileAttributes.class);
            this.created = attr.creationTime().toString();
            this.modified = attr.lastAccessTime().toString();
        } catch (IOException e) {
        }
    }


    public FileDataDto getFileData() {
        return new FileDataDto(this.path.toString(), this.created, this.modified, this.dangerLevel, this.dangerRate, this.verifierMessage);
    }

    public void setVerifierMessage(String verifierMessage) {
        this.verifierMessage = verifierMessage;
    }

    public void setDangerRateAndLevel(FileContentVerifier verifier) {
        this.dangerRate = verifier.calculateDangerRate();
        this.dangerLevel = verifier.calculateDangerLevel(this.dangerRate);
    }
}
