package cloud.autotests.data;

public enum DefectActionMenuItem {
    CLOSE("Close"),
    RENAME("Rename"),
    CREATE_ISSUE("Create issue"),
    LINK_ISSUE("Link issue"),
    DELETE("Delete");

    private final String displayedName;

    DefectActionMenuItem(String displayedName) {
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
