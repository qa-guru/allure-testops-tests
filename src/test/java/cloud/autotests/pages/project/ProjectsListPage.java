package cloud.autotests.pages.project;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ProjectsListPage {

    public final CreateProjectPopup createProjectPopup = new CreateProjectPopup();
    private final ElementsCollection projectNamesCollection =  $$(".ProjectRow__name");

    @Step("Open projects list page")
    public void openPage() {
        open("");
    }

    @Step("Create new project with name [{projectName}]")
    public void createNewProject(String projectName) {
        $(byText("New project")).click();
        createProjectPopup.fillNameField(projectName);
        createProjectPopup.clickSubmitButton();
    }

    @Step("Find project [{projectName}]")
    public void findProject(String projectName) {
        $("[type='search']").setValue(projectName).pressEnter();
    }

    public void filterProject(String projectName) {
        step("confirm the project {projectName} deletion", () -> {
            $("input.HomeLayout__search").setValue(projectName);
            sleep(500); // иначе через раз падает
        });
    }

    @Step("Verify project [{projectName}] contains in projects list")
    public void checkThatProjectsListContainsProject(String projectName) {
        projectNamesCollection.find(text(projectName)).shouldBe(visible);
    }

    @Step("Verify project [{projectName}] don't contains in projects list")
    public void checkThatProjectsListDoNotContainsProject(String projectName) {
        projectNamesCollection.find(text(projectName)).shouldNotBe(visible);
    }

}
