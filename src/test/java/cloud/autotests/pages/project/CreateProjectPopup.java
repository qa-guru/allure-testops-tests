package cloud.autotests.pages.project;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPopup {

    private final SelenideElement nameInput = $(byName("name"));

    @Step("Fill field [Name] with value {projectName}")
    public void fillNameField(String projectName) {
        nameInput.setValue(projectName);
    }

    @Step("Click [Submit] button")
    public void clickSubmitButton() {
        $(byName("submit")).click();
    }

    @Step("Verify empty name field has error message")
    public void checkThatEmptyNameFieldHasErrorMessage() {
        nameInput.closest("label").find(".Form__error")
                .shouldHave(text("Name is required"));
    }

}
