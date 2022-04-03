package cloud.autotests.api.authorization;

import cloud.autotests.config.App;
import cloud.autotests.helpers.AllureRestAssuredFilter;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static AuthorizationResponseDto getAuthorization() {
        String json = given()
                    .filter(AllureRestAssuredFilter.withCustomTemplates())
                    .formParam("grant_type", "apitoken")
                    .formParam("scope", "openid")
                    .formParam("token", App.config.userToken())
                .when()
                    .post("/api/uaa/oauth/token")
                .then()
                    .statusCode(200)
                    .extract().response().asString();
        return AuthorizationResponseDto.fromJson(json);
    }

    public static String getAuthorizationCookie() {
        String xsrfToken = getAuthorization().getJti();
        return given()
                    .filter(AllureRestAssuredFilter.withCustomTemplates())
                    .header("X-XSRF-TOKEN", xsrfToken)
                    .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                    .formParam("username", App.config.userLogin())
                    .formParam("password", App.config.userPassword())
                .when()
                    .post("/api/login/system")
                .then()
                    .statusCode(200).extract().response()
                    .getCookie("ALLURE_TESTOPS_SESSION");
    }

}
