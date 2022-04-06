package cloud.autotests.pages.login;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @Step("Open login page")
    public void open() {
        Selenide.open("/login");
    }

    @Step("Log in to account with login {username}")
    public void signIn(String username, String password) {
        $(byName("username")).setValue(username);
        $(byName("password")).setValue(password);
        $("button[type='submit']").click();
    }

    @Step("Verify user successful sign out")
    public void checkThatUserSignOut() {
        $(".SystemLoginLayout__footer").shouldHave(
                text("Don't have an account?"),
                text("Sign Up!")
        );
    }

}
