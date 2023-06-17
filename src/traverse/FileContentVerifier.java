package traverse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static traverse.FileContentType.*;
import static traverse.FileType.*;

public class FileContentVerifier {

    private final File file;
    private final Pattern patternPesel = Pattern.compile("[0-9]{2}[0-1][0-9][0-3][0-9][0-9]{5}");
    private final Pattern patternPostCode = Pattern.compile("[0-9]{2}-[0-9]{3}");
    private final Pattern patternName = Pattern.compile("([A-Z][a-z]+) ([A-Z][a-z]+)");
    private final Pattern patternPhone = Pattern.compile("(([0-9]{3})[ ]{0,1}([0-9]{3}[ ]{0,1}[0-9]{3}))");
    private final Pattern patternEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private List<FileScanOutput> fileScanOutput;

    public FileContentVerifier(File file) {
        this.file = file;
    }

    public void verify() throws IOException {
        fileScanOutput = scanFile(provideFileType());
    }
    private List<FileScanOutput> scanFile(FileType fileType) throws IOException {
        return switch (fileType) {
            case TEXT -> scanTextFile();
            case PDF -> scanPdfFile();
            case XLSX -> scanXlsxFile();
            case DOCX -> scanDocxFile();
            case UNKNOWN -> null;
        };
    }

    private List<FileScanOutput> scanDocxFile() {
        return List.of();
    }

    private List<FileScanOutput> scanXlsxFile() {
        return List.of();
    }

    private List<FileScanOutput> scanPdfFile() {
        return List.of();
    }

    private FileType provideFileType() {

        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);

        return switch (extension) {
            case "pdf" -> PDF;
            case "txt" -> TEXT;
            case "xlsx" -> XLSX;
            case "docx" -> DOCX;
            default -> UNKNOWN;
        };
    }

    public int calculateDangerRate() {
        return fileScanOutput.stream()
                .mapToInt(this::calculateDangerLevelForRecord)
                .max().orElse(0);
    }

    private int calculateDangerLevelForRecord(FileScanOutput fileScanOutput) {
        int weight = 0;
        if (fileScanOutput.hasPesel()) {
            weight += PESEL.getWeight();
        }
        if (fileScanOutput.hasPostCode()) {
            weight += POSTCODE.getWeight();
        }
        if (fileScanOutput.hasPhone()) {
            weight += PHONE.getWeight();
        }
        if (fileScanOutput.hasEmail()) {
            weight += EMAIL.getWeight();
        }
        if (fileScanOutput.hasName()) {
            weight += NAME.getWeight();
        }
        return weight;
    }

    public String createMessage() {
        return fileScanOutput.stream()
                .map(this::provideMessages)
                .flatMap(Collection::stream)
                .collect(Collectors.joining("|"));
    }

    private Set<String> provideMessages(FileScanOutput fileScanOutput) {
        Set<String> messages = new HashSet<>();
        if (fileScanOutput.hasPesel()) {
            messages.add(PESEL.getLabel());
        }
        if (fileScanOutput.hasPostCode()) {
            messages.add(POSTCODE.getLabel());
        }
        if (fileScanOutput.hasPhone()) {
            messages.add(PHONE.getLabel());
        }
        if (fileScanOutput.hasEmail()) {
            messages.add(EMAIL.getLabel());
        }
        if (fileScanOutput.hasName()) {
            messages.add(NAME.getLabel());
        }
        return messages;
    }

    public List<FileScanOutput> scanTextFile() throws IOException {

        List<FileScanOutput> outputs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            FileScanOutput output = new FileScanOutput();
            if (hasPesel(line)) {
                output.setHasPesel(true);
            }
            if (hasPostCode(line)) {
                output.setHasPostCode(true);
            }
            if (hasName(line)) {
                output.setHasName(true);
            }
            if (hasPhone(line)) {
                output.setHasPhone(true);
            }
            if (hasEmail(line)) {
                output.setHasEmail(true);
            }
            outputs.add(output);
        }
        return outputs;
    }

    private boolean hasEmail(String text) {
        return patternEmail.matcher(text).find();
    }

    private boolean hasPhone(String text) {
        return patternPhone.matcher(text).find();
    }

    private boolean hasPesel(String text) {
        return patternPesel.matcher(text).find();
    }

    private boolean hasPostCode(String text) {
        return patternPostCode.matcher(text).find();
    }

    private boolean hasName(String text) {
        return patternName.matcher(text).find();
    }
    public static DangerLevel calculateDangerLevel(int dangerRate) {
        if(dangerRate < 5)  {
            return DangerLevel.GREEN;
        }
        if (dangerRate < 15) {
            return DangerLevel.YELLOW;
        }
        return DangerLevel.RED;
    }
}
