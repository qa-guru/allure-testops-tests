package cloud.autotests.api;

import cloud.autotests.api.model.Authorization;
import cloud.autotests.config.App;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static Authorization getAuthorizationResponse() {
        RestAssured.baseURI = App.config.apiUrl();
        return given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .formParam("grant_type", "apitoken")
                .formParam("scope", "openid")
                .formParam("token", App.config.userToken())
                .when()
                .post("/api/uaa/oauth/token")
                .then()
                .statusCode(200)
                .extract().as(Authorization.class);
    }
}
