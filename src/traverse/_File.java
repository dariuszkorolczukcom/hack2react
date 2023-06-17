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

    _File(File file) throws IOException {
        this.path = Path.of(file.getPath());
        BasicFileAttributes attr = Files.readAttributes(this.path, BasicFileAttributes.class);
        this.created = attr.creationTime().toString();
        this.modified = attr.lastAccessTime().toString();
    }

    public FileDataDto getFileData() {
        return new FileDataDto(this.path.toString(), this.created, this.modified);
    }
}
