package cloud.autotests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private SelenideElement logo = $(".BrandLayout__logo");
    private SelenideElement nameInput = $("[name='username']");
    private SelenideElement passwordInput = $("[name='password']");
    private SelenideElement submitButton = $("[type='submit']");
    private SelenideElement signUpLink = $(".link");

    @Step("Open login page")
    public LoginPage openLoginPage() {
        open("");
        return this;
    }

    @Step("Check if page elements are present")
    public LoginPage assertLoginPageIsDisplayed() {
        logo.shouldBe(visible);
        nameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        submitButton.shouldBe(visible);
        signUpLink.shouldBe(visible);
        return this;
    }

    @Step("Click on sign up link")
    public void clickSignUpLink() {
        signUpLink.click();
    }
}
