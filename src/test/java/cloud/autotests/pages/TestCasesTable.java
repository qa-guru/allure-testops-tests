package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TestCasesTable {

    private ElementsCollection rows = $$(".LoadableTree__view > li");

    @Step("Check test cases table size")
    public void shouldHaveSize(int expectedSize) {
        rows.shouldHave(size(expectedSize));
    }

    public void navigateToTestByStatus(String statusName) {
        $$(".LoadableTree__view > li").findBy(text(statusName)).click();
    }
}
