package cloud.autotests.tests;

import cloud.autotests.pages.LoginPage;
import cloud.autotests.pages.SignUpPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

@Story("Login page tests")
public class LoginPageTests extends TestBase {

    LoginPage loginPage = new LoginPage();
    SignUpPage signUpPage = new SignUpPage();

    @Test
    @DisplayName("Open login page")
    void openLoginPage() {
        loginPage
                .openLoginPage()
                .assertLoginPageIsDisplayed();
    }

    @Test
    @DisplayName("Open sign up page")
    void openSignUpPage() {
        loginPage
                .openLoginPage()
                .clickSignUpLink();
        signUpPage
                .assertSignUpPageIsDisplayed();
    }
}
