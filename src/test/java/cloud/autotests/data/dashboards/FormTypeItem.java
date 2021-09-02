package cloud.autotests.data.dashboards;

public enum FormTypeItem {
    MARKDOWN("Markdown"),
    LAUNCH_ANALYTICS("Launch Analytics"),
    LAUNCH_STATISTIC_TREND("Launch statistic Trend"),
    TEST_CASE_PIE_CHART("Test Case Pie Chart"),
    AUTOMATION_TREND("Automation Trend"),
    TOP_TEST_CASES("Top Test Cases"),
    TEST_CASE_TREE_MAP_CHART("Test Case Tree Map Chart"),
    LAUNCHES("Launches");

    private final String displayedName;

    FormTypeItem(String displayedName) {
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
