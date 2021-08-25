package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Story("Profile tests")
public class ProfileTests extends TestBase {

    @WithLogin
    @Test
    void profilePageShouldContainValidDataTest() {
        open("");
        $(".Avatar").click();
        $$(".UserMenu__menu .Menu__item")
                .find(text("Your profile"))
                .click();
        $$(".PaneSection").find(text("Username")).should(text("testuser"));
    }


}