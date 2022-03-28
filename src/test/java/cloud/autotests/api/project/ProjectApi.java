package cloud.autotests.api.project;

import cloud.autotests.api.Authorization;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.CoreMatchers.is;

public class ProjectApi {

    public static Response createProject(ProjectRequestBody requestBody) {
        return
                given()
                        .contentType(JSON)
                        .header("Authorization", "Bearer " + new Authorization().getAccessToken())
                        .body(requestBody.toString())
                        .when()
                        .post("/api/rs/project")
                        .then()
                        .statusCode(200)
                        .body("name", is(requestBody.getProjectName()))
                        .body("isPublic", is(requestBody.getIsPublic()))
                        .extract().response();
    }

}
