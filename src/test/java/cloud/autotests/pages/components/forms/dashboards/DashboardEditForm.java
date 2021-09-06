package cloud.autotests.pages.components.forms.dashboards;

import cloud.autotests.pages.components.forms.FormBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

public class DashboardEditForm extends FormBase {

    private SelenideElement nameInput = getContent().$(".Input");
    private SelenideElement nameErrorMessage = getContent().$(".Form__error");

    private SelenideElement formControls = getForm().$(".Form__controls");
    private SelenideElement cancelButton = formControls.find(byText("Cancel"));
    private SelenideElement submitButton = formControls.find(byText("Submit"));

    @Step("Set value '{value}' in input field 'Name'")
    public DashboardEditForm setNameInput(String value) {
        //nameInput.clear();
        nameInput.val(value);
        return this;
    }

    @Step("Check that form name is '{name}'")
    public void checkThatFormNameIs(String name) {
        getFormName().shouldHave(text(name));
    }

    @Step("Check that 'Name' error message is '{errorMessage}'")
    public DashboardEditForm checkThatNameErrorMessageIs(String errorMessage) {
        nameErrorMessage.shouldHave(text(errorMessage));
        return this;
    }

    @Step("Check that form is closed")
    public DashboardEditForm checkThatFormIsClosed() {
        getForm().shouldNot(visible);
        return this;
    }

    @Step("Click close button on form")
    public DashboardEditForm clickCloseButton() {
        getCloseButton().click();
        return this;
    }

    @Step("Click submit button on form")
    public DashboardEditForm clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click cancel button on form")
    public DashboardEditForm clickCancelButton() {
        cancelButton.click();
        return this;
    }

}