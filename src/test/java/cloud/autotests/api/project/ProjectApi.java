package cloud.autotests.api.project;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.EndPoints;

import static io.restassured.RestAssured.given;

public class ProjectApi extends BaseApi {

    public static ProjectResponseDto createProject(CreateProjectRequestDto project) {
        String json = given().spec(defaultRequestSpec)
                    .body(project.toJson())
                .when()
                    .post(EndPoints.PROJECT_CREATE)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return ProjectResponseDto.fromJson(json);
    }

    public static ProjectResponseDto[] getProjectsByName(String projectName) {
        String json = given().spec(defaultRequestSpec)
                .queryParam("name", projectName)
                .when()
                    .get(EndPoints.PROJECT_FIND_BY_NAME)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return ProjectResponseDto.fromJsonToArray(json);
    }

    public static void deleteProject(int projectId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", projectId)
                .when()
                    .delete(EndPoints.PROJECT_BY_ID)
                .then()
                    .statusCode(204);
    }

}
