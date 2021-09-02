package cloud.autotests.pages.components.forms;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FormBase {

    private SelenideElement self = $(".ReactModal__Content");
    private SelenideElement header = self.$(".Modal__header");
    private SelenideElement formName = self.$(".Modal__name");
    private SelenideElement closeButton = header.$(".Button");
    private SelenideElement content = self.$(".Modal__content");


    public SelenideElement getForm() {
        return self;
    }

    public SelenideElement getFormName() {
        return formName;
    }

    public SelenideElement getCloseButton() {
        return closeButton;
    }

    public SelenideElement getContent() {
        return content;
    }
}