package cloud.autotests.api.dashboard;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class DashboardApi extends BaseApi {

    public static DashboardResponseDto createDashboard(CreateDashboardRequestDto dashboard) {
        String json = given().spec(defaultRequestSpec)
                    .body(dashboard.toJson())
                .when()
                    .post(EndPoints.DASHBOARD_CREATE)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return DashboardResponseDto.fromJson(json);
    }

    public static void deleteDashboard(int dashboardId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", dashboardId)
                .when()
                    .delete(EndPoints.DASHBOARD_DELETE)
                .then()
                    .statusCode(anyOf(is(200), is(202), is(204)));
    }

}
