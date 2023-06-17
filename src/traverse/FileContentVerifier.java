package traverse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileContentVerifier {

    private final File file;
    private Pattern patternPesel = Pattern.compile("[0-9]{2}[0-1][0-9][0-3][0-9][0-9]{5}");
    private Pattern patternPostCode = Pattern.compile("[0-9]{2}-[0-9]{3}");
    private FileScanOutput fileScanOutput;

    public FileContentVerifier(File file) {
        this.file = file;
    }

    public void verify() throws IOException { //TODO scan file
        //TODO validate extension
        //file type
        fileScanOutput = scanFile(provideFileType());
    }

    private FileScanOutput scanFile(FileType fileType) throws IOException {
        return switch (fileType) {
            case TEXT -> scanTextFile();
            case PDF -> scanPdfFile();
            case UNKNOWN -> null;
        };
    }

    private FileScanOutput scanPdfFile() {
        FileScanOutput scanResult = new FileScanOutput();
        return scanResult;
    }

    private FileType provideFileType() {
        return FileType.TEXT;
    }

    public DangerLevel calculateDangerLevel() {
        if (fileScanOutput.hasPesel() && fileScanOutput.hasPostCode()) {
            return DangerLevel.RED;
        }
        if (fileScanOutput.hasPesel() || fileScanOutput.hasPostCode()) {
            return DangerLevel.YELLOW;
        }
        return DangerLevel.GREEN;
    }

    public String createMessage() {
        String message = "";
        if (fileScanOutput.hasPostCode()) {
            message += " zawiera kod pocztowy ";
        }
        if (fileScanOutput.hasPesel()) {
            message += " zawiera pesel ";
        }
        return message;
    }

    public FileScanOutput scanTextFile() throws IOException {

        FileScanOutput output = new FileScanOutput();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            if (hasPesel(line)) {
                output.setHasPesel(true);
            }
            if (hasPostCode(line)) {
                output.setHasPostCode(true);
            }
        }
        return output;
    }

    private boolean hasPesel(String text) {
        return patternPesel.matcher(text).find();
    }

    private boolean hasPostCode(String text) {
        return patternPostCode.matcher(text).find();
    }
}
