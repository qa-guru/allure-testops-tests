package cloud.autotests.data.dashboards;

public enum FormTreeItem {
    FEATURES("Features"),
    SUITES("Suites");


    private final String displayedName;

    FormTreeItem(String displayedName) {
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
