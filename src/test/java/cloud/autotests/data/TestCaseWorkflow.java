package cloud.autotests.data;

public enum TestCaseWorkflow {
    DEFAULT_AUTOMATED("Default Automated"),
    DEFAULT_MANUAL("Default Manual"),
    EXTENDED_MANUAL("Extended Manual");

    private final String workflow;

    TestCaseWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public String getWorkflow() {
        return workflow;
    }

    @Override
    public String toString() {
        return workflow;
    }

}
