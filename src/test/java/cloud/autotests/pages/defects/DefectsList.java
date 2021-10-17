package cloud.autotests.pages.defects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class DefectsList {

    private ElementsCollection defectsList = $$(".DefectsList__content li.list-row");

    @Step("Going to defect with name={defectName}")
    public DefectInformation navigateTo(String defectName){
        defectsList.find(Condition.text(defectName)).click();
        return new DefectInformation();
    }

    @Step("Checking is defect with name {defectName} in list")
    public boolean isDefectInList(String defectName){
        return defectsList.filter(Condition.text(defectName)).size() > 0 ? true : false;
    }

    @Step("Checking is defect with name {defectName} was deleted")
    public void checkDefectDeletion(String defectName){
        defectsList.filter(Condition.text(defectName)).shouldHave(size(0));
    }
}
