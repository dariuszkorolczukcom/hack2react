package main;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
        
        File dir = new File("./");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
          for (File child : directoryListing) {
        	  BasicFileAttributes attr = Files.readAttributes(Path.of(child.getPath()), BasicFileAttributes.class);

        	  System.out.println("creationTime: " + attr.creationTime());
        	  System.out.println("lastAccessTime: " + attr.lastAccessTime());
        	  System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        	  System.out.println("isDirectory: " + attr.isDirectory());
        	  System.out.println("isOther: " + attr.isOther());
        	  System.out.println("isRegularFile: " + attr.isRegularFile());
        	  System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        	  System.out.println("size: " + attr.size());
            System.out.println(child);
          }
        } else {
          
        }
        
    }
}
