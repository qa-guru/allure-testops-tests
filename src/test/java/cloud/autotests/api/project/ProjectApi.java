package cloud.autotests.api.project;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.EndPoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ProjectApi extends BaseApi {

    public static Integer createProjectAndGetId(ProjectDto requestBody) {
        return getCreateProjectResponse(requestBody).path("id");
    }

    public static Integer getProjectId(String projectName) {
        return getProjectResponse(projectName).path("id[0]");
    }

    public static void removeProject(int projectId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", projectId)
                .when()
                    .delete(EndPoints.PROJECT_BY_ID)
                .then()
                    .statusCode(204);
    }

    private static Response getCreateProjectResponse(ProjectDto requestBody) {
        return given().spec(defaultRequestSpec)
                    .body(requestBody.toJson())
                .when()
                    .post(EndPoints.PROJECT)
                .then()
                    .statusCode(200)
                    .body("name", is(requestBody.getProjectName()))
                    .body("isPublic", is(requestBody.getIsPublic()))
                    .extract()
                    .response();
    }

    private static Response getProjectResponse(String projectName) {
        return given().spec(defaultRequestSpec)
                    .queryParam("name", projectName)
                .when()
                    .get("/api/rs/project")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();
    }

}
