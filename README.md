# GaugeListStep

## Create instances

> After import the package, refer to the below instances.

    ConceptStepList getConceptStepList = new ConceptStepList();
    ScenarioStepList getScenarioStepList = new ScenarioStepList();
    
## Usage

> The below is one of the ways to get all steps. Firstly, the getAllStepsInScenario method returns the scenario names as a List, then these names are searched in Concept files
via the getAllStepsInConcept method. If there is not any Concept Header related to your scenario step, then just the name of the scenario step is recorded. 
You can implement the below code into your appropriate Execution Hook (BeforeScenario, AfterScenario, etc.)


     List<String> scenarioSteps = getScenarioStepList.getAllStepsInScenario(context.getCurrentScenario().getName(),"/specs/");
     getConceptStepList.getAllStepsInConcept(scenarioSteps,"/concepts/");
     
## Drawback

> Dynamic variables in scenario names are have not been supported yet.
    
    
