package cloud.autotests.pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Alerts {

    private SelenideElement toastifyCloseButton = $(".Toastify__close-button");

    public SelenideElement getToastifyCloseButton() {
        return toastifyCloseButton;
    }

    public void confirmAlert() {
        Selenide.confirm();
    }

    public void dismissAlert() {
        Selenide.dismiss();
    }
}
