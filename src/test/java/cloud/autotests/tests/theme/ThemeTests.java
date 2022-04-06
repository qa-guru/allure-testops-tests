package cloud.autotests.tests.theme;

import cloud.autotests.config.App;
import cloud.autotests.helpers.LocalStorageHelper;
import cloud.autotests.tests.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class ThemeTests extends BaseTest {

    @CsvSource(value = {
            "light,dark,#1e293b",
            "dark,light,#fff"
    })
    @ParameterizedTest(name = "Change theme from {0} to {1}")
    void changeOfThemeTest(String beforeTheme, String afterTheme, String hexBackgroundColor) {
        step("Open the main page", () ->
                open(""));
        step("Install a " + beforeTheme + " theme", () -> {
            LocalStorageHelper.setItem("AS_THEME", beforeTheme);
            refresh();
        });
        step("Fill login form", () -> {
            $(byName("username")).setValue(App.config.userLogin());
            $(byName("password")).setValue((App.config.userPassword()))
                    .pressEnter();
        });
        step("Click on the user menu", () ->
                $("span.LogoWithPlaceholder").click());
        step("Choose a " + afterTheme + " theme", () ->
                $("label.UserMenu__checkbox").parent().click());
        step("checking the display of the dark theme", () ->
                assertThat(
                        $("html")
                        .getAttribute("style")
                        .split("; ")[0]
                        .split(":")[1]
                )
                        .isEqualTo(hexBackgroundColor));
    }

}
