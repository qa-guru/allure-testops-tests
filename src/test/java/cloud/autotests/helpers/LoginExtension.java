package cloud.autotests.helpers;

import cloud.autotests.api.Authorization;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        String authorizationResponse =
                new Authorization().getAuthorizationResponse().asString();

        open("/favicon.ico");
        localStorage().setItem("AS_AUTH_2", authorizationResponse);
    }
}
