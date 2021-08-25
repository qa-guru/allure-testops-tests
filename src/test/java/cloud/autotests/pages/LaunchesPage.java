package cloud.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LaunchesPage {
    private SelenideElement header = $(".BreadCrumbs");

    @Step
    public void checkHeaderText() {
        header.shouldHave(Condition.text("Launches"));
    }
}
