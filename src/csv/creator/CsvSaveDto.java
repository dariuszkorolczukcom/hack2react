package csv.creator;

public class CsvSaveDto {

    public final static String CSV_HEADER = "FilePath,CreatedTime,LastModifiedTime\n";
    private final String path;
    private final String created;
    private final String modified;

    public CsvSaveDto(String absolutePath, String name, String fileTime) {
        this.path = absolutePath;
        this.created = name;
        this.modified = fileTime;
    }

    public String toCsv() {
        return String.format(
                "\"%s\",\"%s\",\"%s\"%n",
                this.path, this.created, this.modified
        );
    }
}