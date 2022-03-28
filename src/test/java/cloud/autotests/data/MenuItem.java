package cloud.autotests.data;

public enum MenuItem {
    DASHBOARD("Dashboards"),
    TEST_CASES("Test cases"),
    TEST_PLANS("Test plans"),
    LAUNCHES("Launches"),
    ANALYTICS("Analytics"),
    DEFECTS("Defects"),
    JOBS("Jobs"),
    SETTINGS("Settings");

    private final String displayedName;

    MenuItem(String displayedName) {
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
