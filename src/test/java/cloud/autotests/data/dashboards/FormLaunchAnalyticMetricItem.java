package cloud.autotests.data.dashboards;

public enum FormLaunchAnalyticMetricItem {
    AVERAGE_TEST_CASES_SUCCESS_RATE("Average Test Cases success rate"),
    AVERAGE_TEST_CASES_DURATION("Average Test Cases duration"),
    SUMMARY_TEST_CASES_DURATION("Summary Test Cases duration"),
    TEST_CASES_COUNT("Test Cases count"),
    TEST_RESULTS_COUNT("Test Results count"),
    RETRIES_COUNT("Retries count"),
    TEST_RESULTS_COUNT_EXCLUDE_RETRIES("Test Results count exclude retries"),
    SUMMARY_RETRIES_DURATION("Summary retries duration"),
    SUMMARY_DURATION_EXCLUDE_RETRIES("Summary duration exclude retries");

    private final String displayedName;

    FormLaunchAnalyticMetricItem(String displayedName) {
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
