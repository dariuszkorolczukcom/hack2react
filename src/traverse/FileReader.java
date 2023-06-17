package traverse;

import java.io.File;
import java.io.IOException;

public class FileReader {
	public Object[][] traverse() throws IOException {
        File dir = new File("./");
        File[] directoryListing = dir.listFiles();
    	Object[][] data = new Object[7][];
    	int i = 0;
        if (directoryListing != null) {
          for (File child : directoryListing) {
        	  _File file = new _File(child);
        	  //file.printAttributes();
              data[i] = file.getTableData();
              System.out.println(i);
            	i++;
          }
        } else {
          
        }
        return data;
	}
}
