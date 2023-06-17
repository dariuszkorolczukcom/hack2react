package traverse;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FileType {
    PDF("pdf"),
    TEXT("txt"),
    XLSX("xlsx"),
    DOCX("docx"),
    UNKNOWN("");

    private static final Map<String, FileType> BY_EXTENSION;

    static {
        BY_EXTENSION = Stream.of(values()).collect(Collectors.toMap(FileType::getExtension, Function.identity()));
    }

    private final String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static FileType fromExtension(String extension) {
        return BY_EXTENSION.getOrDefault(extension, UNKNOWN);
    }
}
