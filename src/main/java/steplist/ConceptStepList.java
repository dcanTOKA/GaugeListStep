package steplist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import static steplist.FileUtility.getListOfFile;

public class ConceptStepList implements StepList {

    public List<String> getAllStepsInConcept(List<String> scenarioSteps, String rootPath) throws FileNotFoundException {

        List<String> allStepsList = new ArrayList<>();

        String srcDir = System.getProperty("user.dir") + rootPath;

        File[] listOfFiles = getListOfFile(srcDir);

        for (String step : scenarioSteps) {

            if (listOfFiles.length <= 0) continue;

            List<String> stepNameWithoutConcept = new ArrayList<>();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    try (Scanner iteratedLines = new Scanner(new BufferedReader(new FileReader(srcDir + listOfFile.getName())))) {
                        Pair<List<String>, List<String>> stepPair = searchScenarioLineByLine(iteratedLines, allStepsList, stepNameWithoutConcept, step);
                        allStepsList = stepPair.getLeft();
                        stepNameWithoutConcept = stepPair.getRight();
                    }
                }
            }
            if (stepNameWithoutConcept.isEmpty()) allStepsList.add(step);
        }
        return allStepsList;
    }

    public Pair<List<String>, List<String>> searchScenarioLineByLine(Scanner iterLines, List<String> allSteps, List<String> stepHasNoConcept, String stepName) {

        boolean isStepFound = false;

        while (iterLines.hasNextLine()) {

            String iteratedLine = iterLines.nextLine();
            String lineName = iteratedLine;

            iteratedLine = iteratedLine.replace("#", "").trim();

            if (iteratedLine.equals(stepName)) {
                isStepFound = true;
                continue;
            }
            if (isStepFound) {
                if (lineName.equals("") || lineName.contains("#")) break;

                iteratedLine = iteratedLine.replace("*", "").trim();

                allSteps.add(iteratedLine);
                stepHasNoConcept.add(iteratedLine);
            }
        }

        return Pair.of(allSteps, stepHasNoConcept);
    }
}
