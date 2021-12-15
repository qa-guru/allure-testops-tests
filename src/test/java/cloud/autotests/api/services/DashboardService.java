package cloud.autotests.api.services;

import cloud.autotests.api.models.dashboard.DashboardRequest;
import cloud.autotests.api.models.dashboard.DashboardResponse;
import io.qameta.allure.Step;

import static cloud.autotests.tests.specs.Specs.requestSpec;
import static io.restassured.RestAssured.given;

public class DashboardService {

    @Step("Create a dashboard via API")
    public DashboardResponse createDashboard(DashboardRequest request) {
        return given()
                .spec(requestSpec)
                .when()
                .body(request)
                .post("/api/rs/dashboard")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(DashboardResponse.class);
    }
}
