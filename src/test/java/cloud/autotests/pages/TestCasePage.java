package cloud.autotests.pages;

import cloud.autotests.data.TestCaseStatus;
import cloud.autotests.data.TestCaseWorkflow;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestCasePage {

    public final TestCasesListPage testCasesTable = new TestCasesListPage();

    private final SelenideElement self = $(".Pane");
    private final SelenideElement tagsSection = self.find(byText("Tags")).closest(".PaneSection");
    private final SelenideElement descriptionPaneSection = self.find(byText("Description")).closest(".PaneSection");
    private final SelenideElement testCaseActionMenu = self.find(".Menu__trigger");
    private final SelenideElement testCaseActionItems = $(".tippy-content");

    @Step("Open test case by id [{testCaseId}] on project by id [{projectId}]")
    public void openPage(int projectId, int testCaseId) {
        open("/project/" + projectId + "/test-cases/" + testCaseId);
        // Добавлена проверка на видимость надписи [Write] в секции [Comments], тк страница загружается частями
        // Например, если страница прогружена не до конца, то нажатие на кнопку [Test case action] (бургер)
        // не вызовет контекстное меню, а лишь отобразит подсказку (как если навести на этот элемент без клика)
        $(".CommentEdit").find(byText("Write")).shouldBe(visible);
    }

    @Step("Get (parse) test case id")
    public int getTestCaseId() {
        String idString = self.find(".TestCaseLayout__id").getText()
                .replaceAll("[^0-9]", "");
        return Integer.parseInt(idString);
    }

    @Step("Edit test case name to [{newName}]")
    public void editTestCaseName(String newName) {
        testCaseActionMenu.click();
        testCaseActionItems.find(byText("Rename")).click();
        $(byName("name")).setValue(newName);
        $(".Modal").find(byName("submit")).click();
    }

    @Step("Edit test case description to [{newDescription}]")
    public void editTestCaseDescription(String newDescription) {
        descriptionPaneSection.find("button").click();
        descriptionPaneSection.find(".MarkdownTextarea__edit").setValue(newDescription);
        descriptionPaneSection.find(byName("submit")).click();
    }

    @Step("Change test case status to [{newStatus}]")
    public void changeTestCaseStatus(TestCaseWorkflow workflow, TestCaseStatus newStatus) {
        testCaseActionMenu.click();
        testCaseActionItems.find(byText("Change status")).click();
        getFormLabelFromChangeStatusPopup("Workflow").click();
        $(byText(workflow.getWorkflow())).click();
        getFormLabelFromChangeStatusPopup("Status").click();
        $(byText(newStatus.getStatus())).click();
        $(".Modal").find(byName("submit")).click();
    }

    @Step("Delete test case")
    public void deleteTestCase() {
        testCaseActionMenu.click();
        testCaseActionItems.find(byText("Delete")).click();
        $(".TestCaseDeleteModal__confirm-button").click();
    }

    @Step("Add new tag [{newTagName}]")
    public void addNewTag(String newTagName) {
        tagsSection.find("button").click();
        $(".Modal").find("input").setValue(newTagName);
        $(byText(String.format("Create \"%s\"", newTagName))).click();
        $(".Modal").find(byName("submit")).click();
    }

    @Step("Verify test case name is [{name}]")
    public void checkThatTestCaseNameIs(String name) {
        self.find(".TestCaseLayout__name").shouldHave(text(name));
    }

    @Step("Verify test case description is [{description}]")
    public void checkThatTestCaseDescriptionIs(String description) {
        descriptionPaneSection.shouldHave(text(description));
    }

    @Step("Verify test case status is [{status}]")
    public void checkThatTestCaseStatusIs(TestCaseStatus status) {
        self.find(".TestCaseLayout__status").shouldHave(text(status.getStatus()));
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

    private SelenideElement getFormLabelFromChangeStatusPopup(String fieldTitle) {
        return $$(".FormLabel__name").find(text(fieldTitle)).closest(".FormLabel").find(".Select");
    }

}
