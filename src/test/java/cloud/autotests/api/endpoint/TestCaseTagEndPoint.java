package cloud.autotests.api.endpoint;

public class TestCaseTagEndPoint {

    public static final String CREATE = "/api/rs/tag";
    public static final String DELETE = CREATE + "/{id}";
    public static final String FIND_BY_TEST_CASE = "/api/rs/testcase/{testCaseId}/tag";
    public static final String SET_BY_TEST_CASE = "/api/rs/testcase/{testCaseId}/tag";

}
