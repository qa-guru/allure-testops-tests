package cloud.autotests.api.testPlan;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.endpoint.TestPlanEndPoint;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class TestPlanApi extends BaseApi {

    public TestPlanResponseDto createTestPlan(CreateTestPlanRequestDto testPlan) {
        String json = given().spec(defaultRequestSpec)
                    .body(testPlan.toJson())
                .when()
                    .post(TestPlanEndPoint.CREATE)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return TestPlanResponseDto.fromJson(json);
    }

    public void deleteTestPlan(int testPlanId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", testPlanId)
                .when()
                    .delete(TestPlanEndPoint.DELETE)
                .then()
                    .statusCode(anyOf(is(200), is(202), is(204)));
    }

}
