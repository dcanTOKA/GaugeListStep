package steplist;

public interface StepList {
    String ALL_STEP_LIST = "allStepList";

    default String getAllStepList() {
        return ALL_STEP_LIST;
    }
}
