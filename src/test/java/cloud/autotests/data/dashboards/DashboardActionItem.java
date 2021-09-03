package cloud.autotests.data.dashboards;

public enum DashboardActionItem {
    ADD_WIDGET("Add widget"),
    FULLSCREEN_MODE("Fullscreen mode"),
    EDIT_DASHBOARD("Edit dashboard"),
    DELETE_DASHBOARD("Delete dashboard");

    private final String displayedName;

    DashboardActionItem(String displayedName) {
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
