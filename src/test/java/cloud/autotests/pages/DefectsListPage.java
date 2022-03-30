package cloud.autotests.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DefectsListPage {

    private final ElementsCollection defectsList = $$(".DefectsList__content > li");

    @Step("Open defects list page by projectId [{projectId}]")
    public void openPage(Integer projectId) {
        open("/project/" + projectId + "/defects");
    }

    @Step("Create new defect with name [{name}]")
    public void createNewDefect(String name, String description) {
        $(".ProjectDefectsLayout__header button").click();
        $(byName("name")).setValue(name);
        $(byName("description")).setValue(description);
        $(byName("submit")).click();
    }

    @Step("Search defect by name [{name}]")
    public void searchDefect(String name) {
        $(byName("search")).setValue(name).pressEnter();
    }

    @Step("Verify defects list contains defect with name [{name}]")
    public void checkThatDefectsListContainsDefect(String name) {
        searchDefect(name);
        defectsList.find(text(name)).shouldBe(visible);
    }

    @Step("Verify defects list don't contains defect with name [{name}]")
    public void checkThatDefectsListDoNotContainsDefect(String name) {
        searchDefect(name);
        defectsList.find(text(name)).shouldNotBe(visible);
    }

}
