package cloud.autotests.pages.defects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class DefectsPage {

    private DefectsList defectsList;
    private DefectInformation defectInformation;

    private SelenideElement newDefectBtn = $(".ProjectDefectsLayout__header button");
    private SelenideElement nameInput = $(".FormLabel__name ~ input");
    private SelenideElement descriptionInput = $("textarea[name='description']");
    private SelenideElement submitBtn = $("button[name='submit']");
    private SelenideElement cancelBtn = $("button[name='cancel']");

    @Step("Creating a new defect with name={name} and desc={description}")
    public DefectsPage createNewDefect(String name, String description){
        newDefectBtn.click();
        nameInput.val(name);
        descriptionInput.val(description);
        submitBtn.click();
        defectInformation = new DefectInformation();
        defectsList = new DefectsList();
        return this;
    }

    public DefectsPage(){
        defectInformation = new DefectInformation();
        defectsList = new DefectsList();
    }

    public DefectsList getDefectsList() {
        return defectsList;
    }

    public DefectInformation getDefectInformation() {
        return defectInformation;
    }
}
