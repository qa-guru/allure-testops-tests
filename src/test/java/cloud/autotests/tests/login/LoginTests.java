package cloud.autotests.tests.login;

import cloud.autotests.config.App;
import cloud.autotests.pages.login.LoginPage;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Login tests")
public class LoginTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Successful login to account")
    void loginTest() {
        loginPage.open();
        loginPage.signIn(App.config.userLogin(), App.config.userPassword());

        projectPage.getSidebar()
                .checkThatUserAuthorizedAs(App.config.userLogin());
    }

    @Test
    @DisplayName("Successful sign out from account")
    void signOutTest() {
        loginPage.open();
        loginPage.signIn(App.config.userLogin(), App.config.userPassword());

        projectPage.getSidebar()
                .signOut();

        loginPage.checkThatUserSignOut();
    }

}
