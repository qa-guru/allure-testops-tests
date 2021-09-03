package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class ThemeTests extends TestBase {

    private void preparingLocalStorage(String theme) {
        localStorage().removeItem("AS_THEME");
        localStorage().setItem("AS_THEME", theme);
        refresh();
    }

    private String backgroundColor() {
        return $("html")
                .getAttribute("style")
                .split("; ")[0]
                .split(":")[1];
    }


    @CsvSource(value = {
            "light,dark,#1e1e1f",
            "dark,light,#fff"
    })
    @WithLogin
    @ParameterizedTest(name = "Change theme from {0} to {1}")
    void changeOfThemeTest(String beforeTheme, String afterTheme, String hexBackgroundColor) {
        step("Open the main page", () ->
                open(""));
        step("Install a " + beforeTheme + " theme", () ->
                preparingLocalStorage(beforeTheme));
        step("Click on the user menu", () ->
                $(".SideMenu").$(".Avatar").click());
        step("Choose a " + afterTheme + " theme", () ->
                // Более простой локатор подобрать не удалось
                $(".UserMenu__menu>.Menu__item:not(.Menu__item_separator):not(.Menu__item_info)").click());
        step("checking the display of the dark theme", () ->
                assertThat(backgroundColor()).isEqualTo(hexBackgroundColor));
    }
}
