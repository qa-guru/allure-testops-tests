package cloud.autotests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SignUpPage {

    private SelenideElement logo = $(".BrandLayout__logo");
    private SelenideElement firstNameInput = $("[name='firstName']");
    private SelenideElement lastNameInput = $("[name='lastName']");
    private SelenideElement emailInput = $("[name='email']");
    private SelenideElement passwordInput = $("[name='password']");
    private SelenideElement repeatPasswordInput = $("[name='passwordRepeat']");
    private SelenideElement submitButton = $("[type='submit']");

    @Step("Open sign up page")
    public SignUpPage openSignUpPage() {
        open("https://allure.autotests.cloud/join");
        return this;
    }

    @Step("Check if page elements are present")
    public SignUpPage assertSignUpPageIsDisplayed() {
        logo.shouldBe(visible);
        firstNameInput .shouldBe(visible);
        lastNameInput.shouldBe(visible);
        emailInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        repeatPasswordInput.shouldBe(visible);
        return this;
    }
}
