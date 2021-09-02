package cloud.autotests.data.dashboards;

public enum FormGroupByItem {
    BY_AUTOMATION("By automation"),
    BY_STATUS("By status"),
    BY_LAST_EXECUTION_STATUS("By last execution status");

    private final String displayedName;

    FormGroupByItem(String displayedName) {
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
