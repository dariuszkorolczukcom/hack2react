package traverse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<TableDataDto> traverse() throws IOException {
        File dir = new File("./");
        File[] directoryListing = dir.listFiles();
        List<TableDataDto> data = new ArrayList<>();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                _File file = new _File(child);
                data.add(file.getTableData());
            }
        }
        return data;
    }
}
