package cloud.autotests.tests;
import cloud.autotests.config.App;
import cloud.autotests.helpers.WithLogin;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

    @Story("SignOut tests")
    public class SignOutTests extends TestBase {

        private static final String NO_ACCOUNT_TEXT = "Don't have an account?";

        @WithLogin
        @Test
        void afterSignOutPageShouldContainValidDataTest() {
            open(App.config.webUrl());
            $(".Avatar").click();
            $$(".UserMenu__menu .Menu__item")
                    .find(text("Sign Out"))
                    .click();
            $$(".SignInLayout__header").shouldHave(CollectionCondition.itemWithText("SIGN IN"));
        }
    }
