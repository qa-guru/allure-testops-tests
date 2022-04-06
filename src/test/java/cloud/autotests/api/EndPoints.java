package cloud.autotests.api;

public class EndPoints {

    // Projects
    public static final String PROJECT_CREATE = "/api/rs/project";
    public static final String PROJECT_FIND_BY_NAME = "/api/rs/project";
    public static final String PROJECT_BY_ID = PROJECT_CREATE + "/{id}";

    // Test cases
    public static final String TEST_CASE = "/api/rs/testcase";
    public static final String TEST_CASE_DELETE = TEST_CASE + "/{id}";

    // Defects
    public static final String DEFECT_CREATE = "/api/rs/defect";
    public static final String DEFECT_DELETE = DEFECT_CREATE + "/{id}";

    // Dashboards
    public static final String DASHBOARD_CREATE = "/api/rs/dashboard";
    public static final String DASHBOARD_DELETE = DASHBOARD_CREATE + "/{id}";

    // Test plans
    public static final String TEST_PLAN_CREATE = "/api/rs/testplan";
    public static final String TEST_PLAN_DELETE = TEST_PLAN_CREATE + "/{id}";

    // Tags
    public static final String TEST_CASE_TAG_CREATE = "/api/rs/tag";
    public static final String TEST_CASE_TAG_DELETE = TEST_CASE_TAG_CREATE + "/{id}";
    public static final String TEST_CASE_TAG_GET = "/api/rs/testcase/{testCaseId}/tag";
    public static final String TEST_CASE_TAG_SET_TAGS = "/api/rs/testcase/{testCaseId}/tag";

}
