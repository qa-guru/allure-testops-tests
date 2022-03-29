package cloud.autotests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestCasePage {

    public final TestCasesTable testCasesTable = new TestCasesTable();

    private final SelenideElement tagsSection = $(byText("Tags")).closest(".PaneSection");

    @Step("Open test case by id [{testCaseId}] on project by id [{projectId}]")
    public void openPage(int projectId, int testCaseId) {
        open("/project/" + projectId + "/test-cases/" + testCaseId);
    }

    @Step("Add new tag [{newTagName}]")
    public void addNewTag(String newTagName) {
        tagsSection.find("button").click();
        $(".Modal").find("input").setValue(newTagName);
        $(byText(String.format("Create \"%s\"", newTagName))).click();
        $(".Modal").find(byName("submit")).click();
    }

    @Step("Verify tags section contains tag")
    public void checkThatTagsSectionContainsTag(String... tags) {
        for (String tag : tags)
            tagsSection.shouldHave(text(tag));
    }

    @Step("Verify tags section don't contains tag")
    public void checkThatTagsSectionDoNotContainsTag(String... tags) {
        for (String tag : tags)
            tagsSection.shouldNotHave(text(tag));
    }

}
