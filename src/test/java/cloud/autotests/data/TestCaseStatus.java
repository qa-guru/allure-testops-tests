package cloud.autotests.data;

public enum TestCaseStatus {

    ACTIVE("Active"),
    DRAFT("Draft"),
    OUTDATED("Outdated"),
    REVIEW("Review");

    private final String status;

    TestCaseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }

}
