package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Story("Profile tests")
public class ProfileTests extends BaseTest {

    @WithLogin
    @Test
    void profilePageShouldContainValidDataTest() {
        open("");
        $(".Avatar").click();
        $$(".UserMenu__menu .Menu__item")
                .find(text("Your profile"))
                .click();
        $$(".PaneSection").find(text("Username")).should(text("testuser"));
        $$(".PaneSection").find(text("Full name")).should(text("Test User"));
        $$(".PaneSection").find(text("Email")).should(text("no@no.no"));
        $$(".PaneSection").find(text("Authorities")).should(text("USER"));
        $$(".PaneSection").find(text("API tokens!")).should(text("Api tokens are used to configure " +
                "integrations. Once created, API Token can't be viewed or changed."));
        $$(".PaneSection").find(text("Filter Mode")).should(text("This magic setting will change the behaviour" +
                " of filters pane on project test cases page. List Mode used to display saved filters at first, when" +
                " View Mode is used to force display of filter controls."));
    }


}
