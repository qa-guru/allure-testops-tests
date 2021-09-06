package cloud.autotests.api;

import cloud.autotests.api.model.Dashboard;
import cloud.autotests.config.App;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class DashboardsApi {

    //region Dashboards
    @Step("Add dashboard using API")
    public String addDashboard(String dashboardName) {
        RestAssured.baseURI = App.config.apiUrl();
        Dashboard dashboard = new Dashboard().setProjectId(App.config.projectId())
                .setName(dashboardName);

        return given()
                .contentType(JSON)
                .body(dashboard)
                .header("Authorization", System.getProperty("accessToken"))
                .when()
                .post("/api/rs/dashboard/")
                .then()
                .statusCode(200)
                .extract().path("id").toString();
    }

    public void deleteDashboard(String dashboardId) {
        RestAssured.baseURI = App.config.apiUrl();

        given()
                .header("Authorization", System.getProperty("accessToken"))
                .when()
                .delete("/api/rs/dashboard/" + dashboardId)
                .then()
                .statusCode(202);
    }

    public JsonArray getDashboardsList(int projectId) {
        RestAssured.baseURI = App.config.apiUrl();

        String response = given()
                .header("Authorization", System.getProperty("accessToken"))
                .when()
                .param("projectId", projectId)
                .param("size", 2000)
                .get("/api/rs/dashboard/")
                .then()
                .statusCode(200)
                .extract().response().asString();
        return JsonParser.parseString(response).getAsJsonObject().getAsJsonArray("content");
    }

    @Step("Delete all tests dashboards using API")
    public void deleteAllDashboards(int projectId) {
        JsonArray dashboardsList = getDashboardsList(projectId);

        for (int i = 0; i < dashboardsList.size(); i++) {
            String dashboardId = dashboardsList.get(i).getAsJsonObject().get("id").getAsString();
            deleteDashboard(dashboardId);
        }
    }
    //endregion

    //region Dashboard widgets
    public JsonArray getWidgetsList(String dashboardId) {
        RestAssured.baseURI = App.config.apiUrl();

        String response = given()
                .header("Authorization", System.getProperty("accessToken"))
                .when()
                .get("/api/rs/dashboard/" + dashboardId)
                .then()
                .statusCode(200)
                .extract().response().asString();

        return JsonParser.parseString(response).getAsJsonObject().getAsJsonArray("widgets");
    }

    public void deleteWidget(String widgetId) {
        RestAssured.baseURI = App.config.apiUrl();

        given()
                .header("Authorization", System.getProperty("accessToken"))
                .when()
                .delete("/api/rs/widget/" + widgetId)
                .then()
                .statusCode(202);
    }

    public void deleteAllWidgets(String dashboardsId) {
        JsonArray widgetList = getWidgetsList(dashboardsId);

        for (int i = 0; i < widgetList.size(); i++) {
            String widgetId = widgetList.get(i).getAsJsonObject().get("id").getAsString();
            deleteWidget(widgetId);
        }
    }
    //endregion
}
