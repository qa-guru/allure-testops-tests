package cloud.autotests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private SelenideElement logo = $(".BrandLayout__logo");
    private SelenideElement nameInput = $("[name='username']");
    private SelenideElement passwordInput = $("[name='password']");
    private SelenideElement submitButton = $("[type='submit']");
    private SelenideElement signUpLink = $(".link");

    public LoginPage openLoginPage() {
        open("");
        return this;
    }

    public LoginPage assertLoginPageIsDisplayed() {
        logo.shouldBe(visible);
        nameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        submitButton.shouldBe(visible);
        signUpLink.shouldBe(visible);
        return this;
    }

    public void clickSignUpLink() {
        signUpLink.click();
    }
}
