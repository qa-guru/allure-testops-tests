package cloud.autotests.api;

import cloud.autotests.api.models.CreateProjectRequest;
import cloud.autotests.api.models.CreateProjectResponse;
import io.restassured.response.Response;

import static cloud.autotests.tests.specs.Specs.requestSpec;
import static cloud.autotests.tests.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;

public class Project {
    public Response createProject(String projectName, boolean isPublic) {
        // todo move to model (object)
        String body = format("{\"isPublic\":true,\"name\":\"%s\"}", projectName);

        return
                given()
                        .when()
                        .header("Authorization",
                                "Bearer " + new Authorization().getAccessToken())
                        .header("Content-Type", "application/json; charset=utf-8")
                        .body(body)
                        .post("/api/rs/project")
                        .then()
                        .statusCode(200)
                        .body("name", is(projectName))
                        .body("isPublic", is(isPublic))
                        .extract().response();
    }

    public CreateProjectResponse createProject(CreateProjectRequest projectRequest) {
        return given()
                .spec(requestSpec)
                .when()
                .body(projectRequest)
                .post("/api/rs/project")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(CreateProjectResponse.class);
    }

    public Response removeProjectById(int id) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/api/rs/project/" + id)
                .then()
                .spec(responseSpec)
                .statusCode(204)
                .extract().response();
    }
}
