package cloud.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestCasesTable {

    private ElementsCollection rows = $$(".LoadableTree__view > li");

    @Step("Check test cases table size")
    public void shouldHaveSize(int expectedSize) {
        rows.shouldHave(size(expectedSize));
    }

    @Step("Choose test case by name: [{nameTestCase}]")
    public void chooseTestCase(String nameTestCase) {
        rows.find(text(nameTestCase)).find(".LoadableTreeNodeCheckbox").click();
    }

    @Step("Click bulk actions button")
    public void clickBulkActionsButton() {
        $(".LoadableTreeControlPanel").$(By.tagName("button")).click();
    }

    @Step("Export test case to CSV")
    public void exportTestCaseToCSV() {
        $(".tippy-content").$(byText("Export to CSV")).click();
        $(".Form__controls").$(byText("Submit")).click();
    }

    @Step("Check export to CSV finished")
    public void checkExportToCSVFinished() {
        $(".Label_status_passed").shouldHave(text("ready"));
        $(".ExportRequest__link").$("a").shouldBe(Condition.enabled);
    }

    public void navigateToTestByStatus(String statusName) {
        $$(".LoadableTree__view > li").findBy(text(statusName)).click();
    }
}
