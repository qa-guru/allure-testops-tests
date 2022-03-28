package cloud.autotests.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TestProperties {
    private ElementsCollection rows = $$(".TestCaseOverview__secondary > div");
    private ElementsCollection items = $$(".PaneSection__name");

    @Step("Check test cases properties size")
    public void shouldHaveSize(int expectedSize) {
        rows.shouldHave(size(expectedSize));
    }

    @Step("Check test cases properties name")
    public void shouldHaveElement(String itemName) {
        items.shouldHave(CollectionCondition.itemWithText(itemName));
    }
}
