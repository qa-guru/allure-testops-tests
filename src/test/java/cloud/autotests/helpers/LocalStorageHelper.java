package cloud.autotests.helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class LocalStorageHelper {

    @Step("Set item in local storage: key = [{key}], value = [{value}]")
    public static void setItem(String key, String value) {
        Selenide.localStorage().setItem(key, value);
    }

}
