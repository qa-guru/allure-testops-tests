package cloud.autotests.api;

public class EndPoints {

    // Projects
    public static final String PROJECT = "/api/rs/project";
    public static final String PROJECT_BY_ID = PROJECT + "/{id}";

    // Tags
    public static final String TEST_CASE_TAG = "/api/rs/testcase/{testCaseId}/tag";
    public static final String TEST_TAG = "/api/rs/tag/{id}";

}
