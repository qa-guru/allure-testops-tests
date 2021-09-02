package cloud.autotests.data.dashboards;

public enum WidgetActionItem {
    EDIT("Edit"),
    CLONE("Clone"),
    DELETE("Delete");

    private final String displayedName;

    WidgetActionItem(String displayedName) {
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
