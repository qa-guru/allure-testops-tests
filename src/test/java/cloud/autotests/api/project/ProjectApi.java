package cloud.autotests.api.project;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.endpoint.ProjectEndPoint;

import static io.restassured.RestAssured.given;

public class ProjectApi extends BaseApi {

    public ProjectResponseDto createProject(CreateProjectRequestDto project) {
        String json = given().spec(defaultRequestSpec)
                    .body(project.toJson())
                .when()
                    .post(ProjectEndPoint.CREATE)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return ProjectResponseDto.fromJson(json);
    }

    public ProjectResponseDto[] getProjectsByName(String projectName) {
        String json = given().spec(defaultRequestSpec)
                .queryParam("name", projectName)
                .when()
                    .get(ProjectEndPoint.FIND_BY_NAME)
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return ProjectResponseDto.fromJsonToArray(json);
    }

    public void deleteProject(int projectId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", projectId)
                .when()
                    .delete(ProjectEndPoint.DELETE)
                .then()
                    .statusCode(204);
    }

}
