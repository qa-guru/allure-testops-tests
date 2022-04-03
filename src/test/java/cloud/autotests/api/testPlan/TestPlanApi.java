package cloud.autotests.api.testPlan;

import cloud.autotests.api.BaseApi;
import cloud.autotests.api.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class TestPlanApi extends BaseApi {

    public static TestPlanResponseDto createTestPlan(CreateTestPlanRequestDto testPlan) {
        String json = given().spec(defaultRequestSpec)
                    .body(testPlan.toJson())
                .when()
                    .post(EndPoints.TEST_PLAN_CREATE)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return TestPlanResponseDto.fromJson(json);
    }

    public static void deleteTestPlan(int testPlanId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", testPlanId)
                .when()
                    .delete(EndPoints.TEST_PLAN_DELETE)
                .then()
                    .statusCode(anyOf(is(200), is(202), is(204)));
    }

}
