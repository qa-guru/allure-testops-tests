package cloud.autotests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {

    private SelenideElement logo = $(".BrandLayout__logo");
    private SelenideElement nameInput = $("[name='username']");
    private SelenideElement passwordInput = $("[name='password']");
    private SelenideElement submitButton = $("[type='submit']");
    private SelenideElement signUpLink = $("[href='/join']");

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

    @Step("Enter text in username input")
    public LoginPage enterUsername(String text) {
        nameInput.sendKeys(text);
        return this;
    }

    @Step("Enter text in password input")
    public LoginPage enterPassword(String text) {
        passwordInput.sendKeys(text);
        return this;
    }

    @Step("Click submit button")
    public LoginPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Assert that input errors are displayed")
    public LoginPage assertNameErrorIsDisplayed() {
        assertThat($(".Form__error").is(visible));
        assertThat($$(".Form__error").size()).isEqualTo(2);
        return this;
    }

    @Step("Click on sign up link")
    public void clickSignUpLink() {
        signUpLink.click();
    }
}
