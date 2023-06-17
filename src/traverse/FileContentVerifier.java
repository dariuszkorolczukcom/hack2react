package traverse;

import java.io.File;

public class FileContentVerifier {

    private final File file;
    public FileContentVerifier(File file) {
        this.file = file;
    }

    public void verify() { //TODO scan file
    }

    public DangerLevel calculateDangerLevel() {
        return DangerLevel.GREEN;
    }

    public String createMessage() {
        return null;
    }
}
