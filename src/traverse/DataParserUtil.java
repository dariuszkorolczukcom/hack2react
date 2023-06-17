package traverse;

import csv.creator.CsvSaveDto;

import java.util.List;

public class DataParserUtil {
    public static List<CsvSaveDto> provideCsvData(List<FileDataDto> fileData) {
        return fileData.stream()
                .map(FileDataDto::toCsvSaveDto)
                .toList();
    }

    public static List<TableDataDto> provideTableData(List<FileDataDto> fileData) {
        return fileData.stream()
                .map(FileDataDto::toTableDataDto)
                .toList();
    }
}
