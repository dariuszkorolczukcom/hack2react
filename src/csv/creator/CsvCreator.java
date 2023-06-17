package csv.creator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static csv.creator.CsvSaveDto.CSV_HEADER;

public class CsvCreator {

    private final String outputPath;
    private final List<CsvSaveDto> fileList;

    public CsvCreator(String outputPath, List<CsvSaveDto> fileList) {
        this.outputPath = outputPath;
        this.fileList = fileList;
    }

    public void createCsv() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(CSV_HEADER);
            for (CsvSaveDto csvDto : fileList) {
                writer.write(csvDto.toCsv());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
