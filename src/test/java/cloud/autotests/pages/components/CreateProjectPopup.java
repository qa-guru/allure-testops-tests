package cloud.autotests.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPopup {
    @Step("Check that error message is `{message}`")
    public void checkErrorMessage(String message) {
        $(".Form__error").shouldHave(text(message));
    }
}
