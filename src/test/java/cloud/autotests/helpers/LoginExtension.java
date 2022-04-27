package cloud.autotests.helpers;

import cloud.autotests.api.authorization.AuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    private static final String ALLURE_TESTOPS_SESSION = "ALLURE_TESTOPS_SESSION";

    @Override
    public void beforeEach(ExtensionContext context) {
        String cookies = AuthorizationApi.getAuthorizationCookie();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, cookies));
    }

}
