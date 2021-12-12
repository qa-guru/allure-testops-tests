package cloud.autotests.api;

import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.restassured.response.Response;

import static cloud.autotests.config.App.config;
import static io.restassured.RestAssured.given;

public class Authorization {

    public Response getAuthorizationResponse() {
        return given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .formParam("grant_type", "apitoken")
                .formParam("scope", "openid")
                .formParam("token", config.userToken())
                .when()
                .post("/api/uaa/oauth/token")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public String getAccessToken() {
      return getAuthorizationResponse().path("access_token");
    }

    public Response authorizationViaApi() {
        String xsrfToken = getAuthorizationResponse().getBody().jsonPath().get("jti");
        return given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .header("X-XSRF-TOKEN", xsrfToken)
                .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                .formParam("username", config.userLogin())
                .formParam("password", config.userPassword())
                .when()
                .post("/api/login/system")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
