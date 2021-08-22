package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class ThemeTests extends TestBase {

    @WithLogin
    @Test
    void changeOfThemeTest() {
        step("open the main page", () ->
                open(""));
        step("checking the display of the light theme", () ->
                assertThat(localStorage().getItem("AS_THEME")).isEqualTo("light"));
        step("click on the user menu", () ->
                $(".SideMenu").$(".Avatar").click());
        step("choose a dark theme", () ->
                $(".UserMenu__menu .Menu__item", 4).click());
        step("checking the display of the dark theme", () ->
                assertThat(localStorage().getItem("AS_THEME")).isEqualTo("dark"));
    }
}
