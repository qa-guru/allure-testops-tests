package cloud.autotests.data.dashboards;

public enum FormTopTestCasesMetricItem {
    DURATION("Duration"),
    SUCCESS_RATE("Success Rate");

    private final String displayedName;

    FormTopTestCasesMetricItem(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }


    @Override
    public String toString() {
        return displayedName;
    }
}
