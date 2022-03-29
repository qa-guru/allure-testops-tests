package cloud.autotests.api.project;

import cloud.autotests.api.Authorization;
import cloud.autotests.api.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.CoreMatchers.is;

public class ProjectApi {

    public static Integer createProjectAndGetId(ProjectRequestBody requestBody) {
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

    private final static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .setContentType(JSON)
            .addHeader("Authorization", "Bearer " + new Authorization().getAccessToken())
            .build();

    private static Response getCreateProjectResponse(ProjectRequestBody requestBody) {
        return given().spec(defaultRequestSpec)
                    .body(requestBody.toString())
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
