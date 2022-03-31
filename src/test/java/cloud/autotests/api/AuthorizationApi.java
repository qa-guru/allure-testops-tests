package cloud.autotests.api;

import cloud.autotests.config.App;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static String getAccessToken() {
        return getAuthorizationForApiResponse().path("access_token");
    }

    public static Response getAuthorizationForApiResponse() {
        return given()
                    .filter(AllureRestAssuredFilter.withCustomTemplates())
                    .formParam("grant_type", "apitoken")
                    .formParam("scope", "openid")
                    .formParam("token", App.config.userToken())
                .when()
                    .post("/api/uaa/oauth/token")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();
    }

    public static Response getAuthorizationForUIResponse() {
        String xsrfToken = getAuthorizationForApiResponse().getBody().jsonPath().get("jti");
        return given()
                    .filter(AllureRestAssuredFilter.withCustomTemplates())
                    .header("X-XSRF-TOKEN", xsrfToken)
                    .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                    .formParam("username", App.config.userLogin())
                    .formParam("password", App.config.userPassword())
                .when()
                    .post("/api/login/system")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();
    }
}
