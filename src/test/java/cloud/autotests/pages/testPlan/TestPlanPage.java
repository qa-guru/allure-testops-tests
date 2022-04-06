package cloud.autotests.pages.testPlan;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestPlanPage {

    @Step("Open test plan page by id [{testPlanId}]")
    public void openPage(int testPlanId) {
        open("/testplan/" + testPlanId);
    }

    @Step("Delete test plan")
    public void deleteTestPlan() {
        $("button.Menu__trigger").shouldNotHave(attribute("disabled")).click();
        $(".tippy-content").find(byText("Delete")).click();
        $(".Button_style_danger").click();
    }

    @Step("Get (parse) test plan id")
    public int getTestPlanId() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String[] splitUrl = currentUrl.split("/");
        String idString = splitUrl[splitUrl.length - 1];
        return Integer.parseInt(idString);
    }

    @Step("Verify that system show created test plan popup")
    public void checkThatSystemShowCreatedTestPlanPopup(String testPlanName) {
        $(".Notification").shouldHave(text("Created " + testPlanName));
    }

    @Step("Verify test plan name is [{name}]")
    public void checkThatTestPlanNameIs(String name) {
        $(".TestPlanHeader__name").shouldHave(text(name));
    }

    @Step("Verify test plan has [{testCasesCount}] test cases")
    public void checkThatTestPlanHasSize(int testCasesCount) {
        $$(".LoadableTree__view li").shouldHave(size(testCasesCount));
    }

}
