package traverse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class _File {
    private final Path path;
    private final String created;
    private final String modified;
    private final BasicFileAttributes attr;

    _File(File file) throws IOException {
        this.path = Path.of(file.getPath());
        this.attr = Files.readAttributes(this.path, BasicFileAttributes.class);
        this.created = this.attr.creationTime().toString();
        this.modified = this.attr.lastAccessTime().toString();
    }

//    public Path getPath() {
//        return this.path;
//    }
//
//    public void printAttributes() {
//        System.out.println(this.path);
//        System.out.println("creationTime: " + this.attr.creationTime());
//        System.out.println("lastAccessTime: " + this.attr.lastAccessTime());
//        System.out.println("lastModifiedTime: " + this.attr.lastModifiedTime());
//        System.out.println("isDirectory: " + this.attr.isDirectory());
//        System.out.println("isOther: " + this.attr.isOther());
//        System.out.println("isRegularFile: " + this.attr.isRegularFile());
//        System.out.println("isSymbolicLink: " + this.attr.isSymbolicLink());
//        System.out.println("size: " + this.attr.size());
//    }

    public TableDataDto getTableData() {
        return new TableDataDto(this.path.toString(), this.created, this.modified);
    }
}
