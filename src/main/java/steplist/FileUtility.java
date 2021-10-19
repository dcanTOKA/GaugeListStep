package steplist;

import java.io.File;

public class FileUtility {

    public static File[] getListOfFile(String sourceDir) {
        File folder = new File(sourceDir);
        return folder.listFiles();
    }
}
