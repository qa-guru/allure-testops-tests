package cloud.autotests.helpers;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        step("Set auth token to browser localstorage", () -> {
            open("/favicon.ico");
            localStorage().setItem("AS_AUTH_2", System.getProperty("AUTH_2"));
        });
    }
}
