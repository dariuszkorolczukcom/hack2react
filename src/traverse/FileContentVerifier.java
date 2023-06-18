package traverse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileContentVerifier {

    private final File file;
    private final Pattern patternPesel = Pattern.compile("[0-9]{2}[0-1][0-9][0-3][0-9][0-9]{5}");
    private final Pattern patternPostCode = Pattern.compile("[0-9]{2}-[0-9]{3}");
    private final Pattern patternName = Pattern.compile("([A-Z][a-z]+) ([A-Z][a-z]+)");
    private final Pattern patternPhone = Pattern.compile("(([0-9]{3})[ ]{0,1}([0-9]{3}[ ]{0,1}[0-9]{3}))");
    private final Pattern patternEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private final FileScanOutput fileScanOutput = new FileScanOutput();

    public FileContentVerifier(File file) {
        this.file = file;
    }

    public DangerLevel calculateDangerLevel(int dangerRate) {
        if (dangerRate < 5) {
            return DangerLevel.GREEN;
        }
        if (dangerRate < 15) {
            return DangerLevel.YELLOW;
        }
        return DangerLevel.RED;
    }

    public void verify() throws IOException {
        scanFile(provideFileType());
    }

    private void scanFile(FileType fileType) throws IOException {
        switch (fileType) {
            case TEXT, CSV -> scanTextFile();
            case PDF -> scanPdfFile();
            case XLSX -> scanXlsxFile();
            case DOCX -> scanDocxFile();
        }
    }

    private void scanTextFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scanString(line);
            }
        }
    }

    private void scanDocxFile() throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis)) {
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            scanString(extractor.getText());
        }
    }

    private void scanXlsxFile() throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file)) {
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        scanString(cell.toString());
                    }
                }
            }
        }
    }

    private void scanPdfFile() throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            scanString(stripper.getText(document));
        }
    }

    private FileType provideFileType() {
        String extension = getFileExtension(file.getName());
        return FileType.fromExtension(extension);
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public int calculateDangerRate() {
        return Arrays.stream(DataType.values())
                .filter(dataType -> dataType.isPresent(fileScanOutput))
                .mapToInt(DataType::getWeight)
                .sum();
    }

    public String createMessage() {
        return Arrays.stream(DataType.values())
                .filter(dataType -> dataType.isPresent(fileScanOutput))
                .map(DataType::getLabel)
                .collect(Collectors.joining("|"));
    }

    private void scanString(String line) {
        detectPattern(line, patternPesel, fileScanOutput::setHasPesel);
        detectPattern(line, patternPostCode, fileScanOutput::setHasPostCode);
        detectPattern(line, patternName, fileScanOutput::setHasName);
        detectPattern(line, patternPhone, fileScanOutput::setHasPhone);
        detectPattern(line, patternEmail, fileScanOutput::setHasEmail);
    }

    private void detectPattern(String line, Pattern pattern, Consumer<Boolean> consumer) {
        if (pattern.matcher(line).find()) {
            consumer.accept(true);
        }
    }
}
