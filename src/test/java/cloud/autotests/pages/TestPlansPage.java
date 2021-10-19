package cloud.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TestPlansPage {
    @Step("Press button New test plan")
    public void clickButtonNewTestPlan() {
        $x("//*[text()='New test plan']").click();
    }

    @Step("Enter name TestPlan")
    public void enterNameNewTestPlan(String testPlanName) {
        $x("//*[@name='name']").setValue(testPlanName);
    }

    @Step("Press button Next")
    public void pressButtonNext() {
        $x("//*[text()='Next']").click();
    }

    @Step("Press button ")
    public void pressButtonCreateTestPlan() {
        $x("//*[@class='TestPlanEdit__controls']//*[text()='Create test plan']").click();
    }

    @Step("Check message ")
    public String checkMessage() {
        String message = Selenide.$(".Toastify__toast--success").shouldBe(Condition.visible).getText();
        return message;
    }

    @Step("Go to page TestPlans")
    public void goToPageTestPlans() {
        $x("//*[@name='test-plans-icon']").click();
    }

    @Step()
    public void findTestPlan(String nameTestPlan) {
        $x("//*[@placeholder='Find test plan']").setValue(nameTestPlan);
    }

    @Step("Check link test plan")
    public String checkLinkTestPlan(String nameTestPlan) {
        String link = $(byXpath("//a[text() ='" + nameTestPlan + "']")).getAttribute("href");
        return link;
    }

    @Step("Get id test plan")
    public String getIdTestPlan() {
        String idTestPlane = $x("//*[@class='TestPlan__id']").getText().substring(1);
        return idTestPlane;
    }
}
