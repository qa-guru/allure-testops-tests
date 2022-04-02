package cloud.autotests.api;

public class EndPoints {

    // ToDo переименовать endpoins под конкретный запрос, то есть, например, DEFECT_CREATE
        // использоваться будет в одном лишь месте

    // Projects
    public static final String PROJECT = "/api/rs/project";
    public static final String PROJECT_BY_ID = PROJECT + "/{id}";

    // Test cases
    public static final String TEST_CASE = "/api/rs/testcase";
    public static final String TEST_CASE_DELETE = TEST_CASE + "/{id}";

    // Defects
    public static final String DEFECT_CREATE = "/api/rs/defect";
    public static final String DEFECT_DELETE = DEFECT_CREATE + "/{id}";

    // Tags
    public static final String TEST_CASE_TAG = TEST_CASE + "/{testCaseId}/tag";
    public static final String TEST_TAG = "/api/rs/tag";
    public static final String TEST_TAG_BY_ID = TEST_TAG + "/{id}";

}
