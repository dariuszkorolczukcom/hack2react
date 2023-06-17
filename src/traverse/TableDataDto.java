package traverse;

public class TableDataDto { //TODO make record if correct java version
    private final String path;
    private final String created;
    private final String modified;

    public TableDataDto(String path, String created, String modified) {
        this.path = path;
        this.created = created;
        this.modified = modified;
    }

    public String getPath() {
        return path;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }
}
