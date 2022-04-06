package cloud.autotests.pages.defect;

import cloud.autotests.data.DefectStatus;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static cloud.autotests.data.DefectActionMenuItem.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DefectPage {

    private final SelenideElement self = $(".Pane");
    private final SelenideElement defectActionItems = $(".tippy-content");
    private final SelenideElement descriptionPaneSection = self.find(byText("Description")).closest(".PaneSection");

    @Step("Open defect page by defectId [{defectID}] on projectId [{projectId}]")
    public void openPage(int projectId, int defectID) {
        open("/project/" + projectId + "/defects/" + defectID);
    }

    @Step("Get (parse) defect id")
    public int getDefectId() {
        String idString = self.find(".Id").getText()
                .replaceAll("[^0-9]", "");
        return Integer.parseInt(idString);
    }

    @Step("Edit defect name to [{newName}]")
    public void editDefectName(String newName) {
        clickDefectActionMenu();
        defectActionItems.find(byText(RENAME.getDisplayedName())).click();
        $(byName("name")).setValue(newName);
        $(byName("submit")).click();
    }

    @Step("Edit defect description to [{newDescription}]")
    public void editDefectDescription(String newDescription) {
        descriptionPaneSection.find("button").click();
        descriptionPaneSection.find(byName("description")).setValue(newDescription);
        descriptionPaneSection.find(byName("submit")).click();
    }

    @Step("Edit defect status to [{status}]")
    public void editDefectStatus(String status) {
        clickDefectActionMenu();
        defectActionItems.find(byText(status)).click();
        self.click();
    }

    @Step("Delete defect")
    public void deleteDefect() {
        clickDefectActionMenu();
        defectActionItems.find(byText(DELETE.getDisplayedName())).click();
        $(".DefectMenu__confirm-button").click();
    }

    @Step("Verify defect name is [{name}]")
    public void checkThatDefectNameIs(String name) {
        self.find(".DefectOverview__name").shouldHave(text(name));
    }

    @Step("Verify defect description is [{description}]")
    public void checkThatDefectDescriptionIs(String description) {
        descriptionPaneSection.shouldHave(text(description));
    }

    @Step("Verify defect status is [{status}]")
    public void checkThatDefectStatusIs(DefectStatus status) {
        self.find(".DefectStatusChange").shouldHave(text(status.getStatus()));
    }

    private void clickDefectActionMenu() {
        self.find(".DefectMenu__menu button").shouldNotHave(attribute("disabled")).click();
    }

}
