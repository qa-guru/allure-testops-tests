package cloud.autotests.api.dashboard;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.endpoint.DashboardEndPoint;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class DashboardApi extends BaseApi {

    public DashboardResponseDto createDashboard(CreateDashboardRequestDto dashboard) {
        String json = given().spec(defaultRequestSpec)
                    .body(dashboard.toJson())
                .when()
                    .post(DashboardEndPoint.CREATE)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return DashboardResponseDto.fromJson(json);
    }

    public void deleteDashboard(int dashboardId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", dashboardId)
                .when()
                    .delete(DashboardEndPoint.DELETE)
                .then()
                    .statusCode(anyOf(is(200), is(202), is(204)));
    }

}
