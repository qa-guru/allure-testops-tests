package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class TestPlansListPage {

    private final ElementsCollection testPlansCollection = $$(".TestPlan");

    @Step("Open test plans list by projectId [{projectId}]")
    public void openPage(int projectId) {
        open("/project/" + projectId + "/testplans");
    }

    @Step("Create new test plan with name [{testPlanName}]")
    public void createNewTestPlan(String testPlanName) {
        $(".ProjectTestPlans__controls .Button").click();
        $(byName("name")).setValue(testPlanName);
        $$("button").find(text("Next")).click();
        $$("button").find(text("Create test plan")).click();
    }

    @Step("Search test plan by name [{name}]")
    public void searchTestPlan(String name) {
        $(".ProjectTestPlans__search").setValue(name).pressEnter();
    }

    @Step("Verify test plans list contains test plan with name [{name}]")
    public void checkThatTestPlansListContainsTestPlan(String name) {
        searchTestPlan(name);
        testPlansCollection.find(text(name)).shouldBe(visible);
    }

    @Step("Verify test plans list don't contains test plan with name [{name}]")
    public void checkThatTestPlansListDoNotContainsTestPlan(String name) {
        searchTestPlan(name);
        testPlansCollection.find(text(name)).shouldNotBe(visible);
    }

}
