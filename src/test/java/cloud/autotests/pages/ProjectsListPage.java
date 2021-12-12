package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class ProjectsListPage {

    private ElementsCollection listOfProjects = $$(".ProjectRow__name");
    private SelenideElement notFoundMessage = $x("//div[@class='ProjectsList ']/div[@class='Empty Empty_center']");

    @Step("Open projects list page")
    public ProjectsListPage openPage() {
        open("");
        return this;
    }

    public ProjectsTable getProjectsTable() {
        return new ProjectsTable();
    }

    @Step("Creating a new project from a main page")
    public ProjectPage createNewProject(String projectName) {
//    public ProjectPage createNewProject(String projectName, boolean isPublic) { // todo
        step("Click on a button 'New project'", () ->
                $("button.Button_style_success").click()
        );
        step("Fill obligatory fields name with {projectName} and abbreviation with {projectAbbr}", () ->
                $("input[name=name]").setValue(projectName)
        );
        step("Click submit, creating {projectName} project", () ->
                $("button.Button_style_success[type=submit]").click()
        );
        return new ProjectPage();
    }

    public void filterProject(String projectName) {
        step("confirm the project {projectName} deletion", () -> {
            $("input.HomeLayout__search").setValue(projectName);
            sleep(500); // иначе через раз падает
        });
    }

    @Step("Type the project name: '{projectName}' into the search field")
    public ProjectsListPage typeProjectNameIntoSearchField(String projectName) {
        $("input[type='search']").val(projectName);
        return this;
    }

    @Step("Check the project with name: '{projectName}' is displayed")
    public void checkTheProjectIsDisplayed(String projectName) {
        listOfProjects.find(text(projectName)).should(visible);
    }

    @Step("Verify 'No projects found message' is displayed")
    public void verifyNoProjectsFoundMessage() {
        notFoundMessage.shouldHave(text("No projects found"));
    }
}
