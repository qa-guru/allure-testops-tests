package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Story("Login tests")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Successful login as testuser")
    void loginTest() {
        step("Open login page", () ->
                open(""));

        step("Fill login form", () -> {
            $(byName("username")).setValue(App.config.userLogin());
            $(byName("password")).setValue((App.config.userPassword()))
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $("img.Avatar__img").shouldHave(
                        attribute("alt", App.config.userLogin())));
    }

    @Test
    @DisplayName("Successful login with localStorage (API + UI)")
    void loginWithCookieTest() {
        step("Get auth token by API and set it to browser localstorage", () -> {
            String authorizationResponse =
                    given()
                            .filter(AllureRestAssuredFilter.withCustomTemplates())
                            .formParam("grant_type", "apitoken")
                            .formParam("scope", "openid")
                            .formParam("token", App.config.userToken())
                            .when()
                            .post("/api/uaa/oauth/token")
                            .then()
                            .statusCode(200)
                            .extract().response().asString();

            step("Open minimal content, because localstorage can be set when site is opened", () ->
                    open("/favicon.ico"));

            step("Set auth token to to browser localstorage", () ->
                    localStorage().setItem("AS_AUTH_2", authorizationResponse));
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $("img.Avatar__img").shouldHave(
                        attribute("alt", App.config.userLogin())));
    }
}
