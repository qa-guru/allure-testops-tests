package cloud.autotests.pages.components.forms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class DeleteForm extends FormBase {

    private SelenideElement deleteButton = getContent().$(".Button");

    @Step("Click close button on form")
    public void clickCloseButton() {
        getCloseButton().click();
    }

    @Step("Click delete button on form")
    public void clickDeleteButton() {
        deleteButton.click();
    }
}