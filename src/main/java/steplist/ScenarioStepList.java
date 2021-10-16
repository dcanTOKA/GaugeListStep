package steplist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ScenarioStepList extends Main {

    public List<String> getAllStepsInScenario(String scenarioName, String rootPath) throws FileNotFoundException {

        List<String> allStepsList = new ArrayList<>();

        String srcDir = System.getProperty("user.dir") + rootPath;

        File[] listOfFiles = getListOfFile(srcDir);


        if (listOfFiles.length > 0) {

            for (File listOfFile : listOfFiles) {

                if (listOfFile.isFile()) {

                    Scanner iteratedLines;

                    iteratedLines = new Scanner(new BufferedReader(new FileReader(srcDir + listOfFile.getName())));

                    Map<String, List<String>> stepsMap = searchConceptLineByLine(iteratedLines, allStepsList, scenarioName);

                    allStepsList = stepsMap.get(getAllStepList());
                }
            }
        }
        return allStepsList;
    }
    public Map<String, List<String>> searchConceptLineByLine(Scanner iterLines, List<String> allSteps, String scenarioName) {

        boolean isStepFound = false;

        while (iterLines.hasNextLine()) {

            String iteratedLine = iterLines.nextLine();

            if (scenarioName.equals(iteratedLine.replace("##", "").trim())) {
                isStepFound = true;
                continue;
            }

            if (isStepFound) {

                if (iteratedLine.contains("*")) {
                    iteratedLine = iteratedLine.replace("*", "");
                    allSteps.add(iteratedLine.trim());
                } else {
                    if (!allSteps.isEmpty() && (iteratedLine.equals("") || iteratedLine.contains("#"))) {
                        break;
                    }
                }
            }
        }

        Map<String, List<String>> allStepInConcept = new HashMap<>();
        allStepInConcept.put(getAllStepList(), allSteps);

        return allStepInConcept;
    }
}
