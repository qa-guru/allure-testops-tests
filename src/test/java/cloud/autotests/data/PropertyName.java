package cloud.autotests.data;

public enum PropertyName {
    TAGS("Tags"),
    HISTORY("History"),
    ISSUE_HISTORY("Issues links"),
    MEMBERS("Members"),
    FIELDS("Fields"),
    MUTES("Mutes"),
    RELATIONS("Relations"),
    DESCRIPTIONS("Description");

    private final String displayedName;

    PropertyName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
