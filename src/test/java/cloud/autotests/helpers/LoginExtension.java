package cloud.autotests.helpers;

import cloud.autotests.api.Authorization;
import cloud.autotests.config.App;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        //todo вернуть обратно api авторизацию после восстановления ее работоспособности
        open("");
        step("Fill login form", () -> {
            $(byName("username")).setValue(App.config.userLogin());
            $(byName("password")).setValue((App.config.userPassword()))
                    .pressEnter();
        });

       /* String authorizationResponse =
                new Authorization().getAuthorizationResponse().asString();

        open("/favicon.ico");
        localStorage().setItem("AS_AUTH_2", authorizationResponse);*/
    }
}
