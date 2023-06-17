package traverse;

import csv.creator.CsvSaveDto;

public class FileDataDto {
    private final String path;
    private final String created;
    private final String modified;

    public FileDataDto(String path, String created, String modified) {
        this.path = path;
        this.created = created;
        this.modified = modified;
    }

    public CsvSaveDto toCsvSaveDto() {
        return new CsvSaveDto(path, created, modified);
    }

    public TableDataDto toTableDataDto() {
        return new TableDataDto(path, created, modified);
    }
}
