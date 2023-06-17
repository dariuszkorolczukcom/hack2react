package traverse;

import java.util.function.Predicate;

public enum DataType {
    PESEL(FileContentType.PESEL, FileScanOutput::hasPesel),
    POSTCODE(FileContentType.POSTCODE, FileScanOutput::hasPostCode),
    PHONE(FileContentType.PHONE, FileScanOutput::hasPhone),
    EMAIL(FileContentType.EMAIL, FileScanOutput::hasEmail),
    NAME(FileContentType.NAME, FileScanOutput::hasName);

    private final FileContentType contentType;
    private final Predicate<FileScanOutput> isPresentPredicate;

    DataType(FileContentType contentType, Predicate<FileScanOutput> isPresentPredicate) {
        this.contentType = contentType;
        this.isPresentPredicate = isPresentPredicate;
    }

    boolean isPresent(FileScanOutput fileScanOutput) {
        return isPresentPredicate.test(fileScanOutput);
    }

    int getWeight() {
        return contentType.getWeight();
    }

    String getLabel() {
        return contentType.getLabel();
    }
}
