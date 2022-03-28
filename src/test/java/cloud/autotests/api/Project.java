package cloud.autotests.api;

import cloud.autotests.api.Authorization;
import io.restassured.response.Response;

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
}
