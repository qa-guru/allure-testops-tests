package cloud.autotests.api.testCase;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.endpoint.TestCaseEndPoint;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;

public class TestCaseApi extends BaseApi {

    public static TestCaseInfoResponseDto createTestCase(CreateTestCaseRequestDto testCase) {
        String jsonResponse = given().spec(defaultRequestSpec)
                    .body(testCase.toJson())
                .when()
                    .post(TestCaseEndPoint.CREATE)
                .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .asString();
        return TestCaseInfoResponseDto.fromJson(jsonResponse);
    }

    public static void deleteTestCase(int testCaseId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", testCaseId)
                .when()
                    .delete(TestCaseEndPoint.DELETE)
                .then()
                    .statusCode(anyOf(is(200), is(202), is(204)));
    }

}
