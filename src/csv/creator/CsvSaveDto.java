package csv.creator;

public class CsvSaveDto {

    public final static String CSV_HEADER = "FilePath,CreatedTime,LastModifiedTime,DangerLevel,DangerMessage\n";
    private final String path;
    private final String created;
    private final String modified;
    private final String dangerLevel;
    private final String message;

    public CsvSaveDto(String absolutePath, String name, String fileTime, String dangerLevel, String message) {
        this.path = absolutePath;
        this.created = name;
        this.modified = fileTime;
        this.dangerLevel = dangerLevel;
        this.message = message;
    }

    public String toCsv() {
        return String.format(
                "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                path, created, modified, dangerLevel, message
        );
    }
}