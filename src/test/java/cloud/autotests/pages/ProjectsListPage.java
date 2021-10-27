package cloud.autotests.pages;

import cloud.autotests.pages.components.CreateProjectPopup;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class ProjectsListPage {

    private CreateProjectPopup createProjectPopup = new CreateProjectPopup();

    @Step("Open projects list page")
    public ProjectsListPage openPage() {
        open("");
        return this;
    }

    public ProjectsTable getProjectsTable() {
        return new ProjectsTable();
    }

    public CreateProjectPopup getCreateProjectPopup() {return new CreateProjectPopup();}

    @Step("Creating a new project from a main page")
    public ProjectPage createNewProject(String projectName) {
//    public ProjectPage createNewProject(String projectName, boolean isPublic) { // todo
        step("Click on a button 'New project'", () ->
                $(byText("New project")).click()
        );
        step("Fill obligatory fields name with {projectName} and abbreviation with {projectAbbr}", () ->
                $("input[name=name]").setValue(projectName)
        );
        step("Click submit, creating {projectName} project", () ->
                $(byText("Submit")).click()
        );
        return new ProjectPage();
    }

    @Step("Creating a new project from a main page")
    public void createNewProjectWithError(String projectName) {
        step("Click on a button 'New project'", () ->
                $(byText("New project")).click()
        );
        step("Fill obligatory fields name with {projectName} and abbreviation with {projectAbbr}", () ->
                $("input[name=name]").setValue(projectName)
        );
        step("Click submit, creating {projectName} project", () ->
                $(byText("Submit")).click()
        );
    }

    public void filterProject(String projectName) {
        step("confirm the project {projectName} deletion", () -> {
            $("input.HomeLayout__search").setValue(projectName);
            sleep(500); // иначе через раз падает
        });
    }
}
