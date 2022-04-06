package cloud.autotests.pages.testCase;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestCasesListPage {

    private final ElementsCollection testCasesCollection = $$(".LoadableTree__view > li");
    private final SelenideElement bulkActionButton = $(".LoadableTreeControlPanel > button");
    private final ElementsCollection bulkActionMenuItems = $$(".Menu__item");

    @Step("Open test cases of project by id [{projectId}]")
    public void openPage(int projectId) {
        open("/project/" + projectId + "/test-cases");
    }

    @Step("Unlock test cases tree")
    public void unlockTestCasesTree() {
        $x("//*[@*='#locked']").closest("button").click();
    }

    @Step("Create new test case with name [{name}]")
    public void createNewTestCase(String name) {
        $(byName("name")).setValue(name).pressEnter();
    }

    @Step("Load test case with name [{name}]")
    public void loadTestCase(String name) {
        testCasesCollection.find(text(name)).click();
    }

    public void navigateToTestByStatus(String statusName) {
        testCasesCollection.find(text(statusName)).click();
    }

    @Step("Select test case by id [{id}]")
    public void selectTestCase(int id) {
        testCasesCollection.find(text(Integer.toString(id))).find("label.Checkbox").click();
    }

    @Step("Select test cases by {ids}")
    public void selectTestCases(int[] ids) {
        for (int id : ids)
            selectTestCase(id);
    }

    @Step("Remove tag [{tagName}] via bulk action")
    public void removeTagViaBulkAction(String tagName) {
        bulkActionButton.click();
        bulkActionMenuItems.find(text("Remove tags")).click();
        $(".Select input").setValue(tagName);
        // Выбор тега реализован через sleep(), поскольку Selenide
        // не может взаимодействовать с выпадающим списком (например, класс .css-hoiezn)
        sleep(1000);
        $(".Select input").pressEnter();
        $(".Modal button[name='submit']").click();
    }

    @Step("Export to CSV")
    public File exportToCsv() throws FileNotFoundException {
        bulkActionButton.click();
        bulkActionMenuItems.find(text("Export to CSV")).click();

        SelenideElement exportPopup = $(".Modal");
        exportPopup.find(byName("submit")).click();
        exportPopup.find(".Label_status_passed").shouldHave(text("ready"), Duration.ofSeconds(20));

        return exportPopup.find(byText("Download")).closest("a").download();
    }

    @Step("Verify test cases table size is [{expectedSize}]")
    public void checkThatTestCasesTableSizeIs(int expectedSize) {
        testCasesCollection.shouldHave(size(expectedSize));
    }

    @Step("Verify test cases table contains test case with name [{testCaseName}]")
    public void checkThatTestCasesTableContainsTestCase(String testCaseName) {
        testCasesCollection.find(text(testCaseName)).shouldBe(visible);
    }

    @Step("Verify test cases table don't contains test case with name [{testCaseName}]")
    public void checkThatTestCasesTableDoNotContainsTestCase(String testCaseName) {
        testCasesCollection.find(text(testCaseName)).shouldNotBe(visible);
    }

}
