package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestCasesTable {

    private final ElementsCollection rows = $$(".LoadableTree__view > li");

    @Step("Check test cases table size")
    public void shouldHaveSize(int expectedSize) {
        rows.shouldHave(size(expectedSize));
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

}
