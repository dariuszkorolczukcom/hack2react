package traverse;

import java.util.List;
import java.util.function.Function;

public class DataParserUtil {
    public static <T> List<T> convertFileDataTo(List<FileDataDto> fileData, Function<FileDataDto, T> converter) {
        return fileData.stream()
                .map(converter)
                .toList();
    }
}
