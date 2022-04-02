package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class TestCasesListPage {

    private final ElementsCollection rows = $$(".LoadableTree__view > li");

    @Step("Open test cases of project by id [{projectId}]")
    public void openPage(Integer projectId) {
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
        rows.find(text(name)).click();
    }

    public void navigateToTestByStatus(String statusName) {
        rows.find(text(statusName)).click();
    }

    @Step("Select test case by id [{id}]")
    public void selectTestCase(int id) {
        rows.find(text(Integer.toString(id)))
                .find("label.Checkbox")
                .click();
    }

    @Step("Remove tag [{tagName}] via bulk action")
    public void removeTagViaBulkAction(String tagName) {
        $(".LoadableTreeControlPanel > button").click();
        $$(".Menu__item ").find(text("Remove tags")).click();
        $(".Select input").setValue(tagName);
        // Выбор тега реализован через sleep(), поскольку Selenide
        // не может взаимодействовать с выпадающим списком (например, класс .css-hoiezn)
        sleep(1000);
        $(".Select input").pressEnter();
        $(".Modal button[name='submit']").click();
    }

    @Step("Verify test cases table size is [{expectedSize}]")
    public void checkThatTestCasesTableSizeIs(int expectedSize) {
        rows.shouldHave(size(expectedSize));
    }

    @Step("Verify test cases table contains test case with name [{testCaseName}]")
    public void checkThatTestCasesTableContainsTestCase(String testCaseName) {
        rows.find(text(testCaseName)).shouldBe(visible);
    }

    @Step("Verify test cases table don't contains test case with name [{testCaseName}]")
    public void checkThatTestCasesTableDoNotContainsTestCase(String testCaseName) {
        rows.find(text(testCaseName)).shouldNotBe(visible);
    }

}
