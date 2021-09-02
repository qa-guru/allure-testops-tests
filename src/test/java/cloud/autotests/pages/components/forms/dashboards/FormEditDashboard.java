package cloud.autotests.pages.components.forms.dashboards;

import cloud.autotests.pages.components.forms.FormBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.sleep;

public class FormEditDashboard extends FormBase {

    private SelenideElement nameInput = getContent().$(".Input");
    private SelenideElement nameErrorMessage = getContent().$(".Form__error");

    private SelenideElement formControls = getForm().$(".Form__controls");
    private SelenideElement cancelButton = formControls.find(byText("Cancel"));
    private SelenideElement submitButton = formControls.find(byText("Submit"));


    @Step("Click close button on form")
    public FormEditDashboard clickCloseButton() {
        getCloseButton().click();
        return this;
    }

    @Step("Click submit button on form")
    public FormEditDashboard clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click cancel button on form")
    public FormEditDashboard clickCancelButton() {
        cancelButton.click();
        return this;
    }

    @Step("Check that form name is '{name}'")
    public void checkThatFormNameIs(String name) {
        getFormName().shouldHave(text(name));
    }

    @Step("Check that form is closed")
    public FormEditDashboard checkThatFormIsClosed() {
        getForm().shouldNotBe(visible);
        return this;
    }

    @Step("Set value '{value}' in input field 'Name'")
    public FormEditDashboard setNameInput(String value) {
        //nameInput.clear();
        nameInput.val(value);
        return this;
    }

    @Step("Check that 'Name' error message is '{errorMessage}'")
    public FormEditDashboard checkThatNameErrorMessageIs(String errorMessage) {
        nameErrorMessage.shouldHave(text(errorMessage));
        return this;
    }
}