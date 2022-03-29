package cloud.autotests.helpers;

import cloud.autotests.api.AuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        String allureCookieName = "ALLURE_TESTOPS_SESSION";
        String cookies = AuthorizationApi.getAuthorizationForUIResponse().getCookie(allureCookieName);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(allureCookieName, cookies));
    }

}
