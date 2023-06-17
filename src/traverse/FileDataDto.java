package traverse;

import csv.creator.CsvSaveDto;

public class FileDataDto {
    private final String path;
    private final String created;
    private final String modified;
    private final DangerLevel dangerLevel;
    private final String verifierMessage;

    public FileDataDto(String path, String created, String modified, DangerLevel dangerLevel, String verifierMessage) {
        this.path = path;
        this.created = created;
        this.modified = modified;
        this.dangerLevel = dangerLevel;
        this.verifierMessage = verifierMessage;
    }

    public CsvSaveDto toCsvSaveDto() {
        return new CsvSaveDto(path, created, modified, dangerLevel.toString(), verifierMessage);
    }

    public TableDataDto toTableDataDto() {
        return new TableDataDto(path, created, modified, dangerLevel.toString(), verifierMessage);
    }
}
