package cloud.autotests.pages.defects;

import cloud.autotests.data.DefectActionMenuItem;
import cloud.autotests.data.DefectStatus;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DefectInformation {

    private SelenideElement defectName = $(".DefectOverview__name");
    private SelenideElement defectDescription = $(By.xpath("//div[text()='Description']/../following-sibling::div//p"));
    private SelenideElement defectStatus = $(".DefectStatusChange div");
    private SelenideElement changeStatusBtn = $(".DefectStatusChange");
    private SelenideElement changeDescBtn = $(By.xpath("//div[text()='Description']/following-sibling::div/button"));
    private SelenideElement descInput = $("textarea[name='description']");
    private SelenideElement submitBtn = $("button[name='submit']");
    private SelenideElement defectActionBtn = $(".DefectMenu__menu button");
    private ElementsCollection menuDefectActionItems = $$(".Menu__item");
    private SelenideElement confirmDelete = $(".DefectMenu__confirm-button");
    private SelenideElement renameInput = $(".Input_size_base");

    @Step("Checking defect name")
    public void checkDefectName (String expectedName){
        defectName.shouldHave(Condition.text(expectedName));
    }

    @Step("Checking defect description")
    public void checkDefectDescription(String expectedDescription){
        defectDescription.shouldHave(Condition.text(expectedDescription));
    }

    @Step("Checking defect status")
    public void checkDefectStatus(DefectStatus expectedStatus){
        defectStatus.shouldHave(Condition.text(expectedStatus.getStatus()));
    }

    @Step("Changing defect status")
    public void changeStatus(){
        changeStatusBtn.click();
    }

    @Step("Changing defect description to {newDescription}")
    public void setChangeDescription(String newDescription){
        changeDescBtn.click();
        descInput.val(newDescription);
        submitBtn.click();
    }

    @Step("Deleting defect")
    public void deleteDefect(){
        defectActionBtn.click();
        menuDefectActionItems.find(Condition.text(DefectActionMenuItem.DELETE.getDisplayedName())).click();
        confirmDelete.click();
    }

    @Step("Changing defect name to {newName}")
    public void renameDefect(String newName){
        defectActionBtn.click();
        menuDefectActionItems.find(Condition.text(DefectActionMenuItem.RENAME.getDisplayedName())).click();
        renameInput.val(newName);
        submitBtn.click();
    }


}
