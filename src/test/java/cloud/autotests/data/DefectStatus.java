package cloud.autotests.data;

public enum DefectStatus {
    OPEN("Open"),
    CLOSED("Closed");

    private final String status;

    DefectStatus(String status) {
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
