package cloud.autotests.api.testCase;

import cloud.autotests.api.BaseApi;
import cloud.autotests.api.EndPoints;

import static io.restassured.RestAssured.given;

public class TestCaseApi extends BaseApi {

    public static TestCaseInfoResponseDto createTestCase(CreateTestCaseRequestDto testCase) {
        String jsonResponse = given().spec(defaultRequestSpec)
                    .body(testCase.toJson())
                .when()
                    .post(EndPoints.TEST_CASE)
                .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .asString();
        return TestCaseInfoResponseDto.fromJson(jsonResponse);
    }

}
