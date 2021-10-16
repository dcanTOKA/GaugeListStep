package steplist;

import java.io.File;

public class Main {

    public static String getAllStepList() {
        return ALL_STEP_LIST;
    }

    private static final String ALL_STEP_LIST = "allStepList";

    public File[] getListOfFile(String sourceDir) {
        File folder = new File(sourceDir);
        return folder.listFiles();
    }
}
