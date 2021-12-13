package cloud.autotests.helpers;

import cloud.autotests.api.Authorization;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        String ALLURE_COOKIE_NAME = "ALLURE_TESTOPS_SESSION";

        String authorizationResponse =
                new Authorization().authorizationViaApi().getCookie(ALLURE_COOKIE_NAME);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_COOKIE_NAME, authorizationResponse));
    }
}
