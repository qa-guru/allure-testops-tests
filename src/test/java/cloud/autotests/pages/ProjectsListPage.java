package cloud.autotests.pages;

import cloud.autotests.pages.components.CreateProjectPopup;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class ProjectsListPage {

    public final CreateProjectPopup createProjectPopup = new CreateProjectPopup();
    public final ProjectsTable projectTable = new ProjectsTable();

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

    public void filterProject(String projectName) {
        step("confirm the project {projectName} deletion", () -> {
            $("input.HomeLayout__search").setValue(projectName);
            sleep(500); // иначе через раз падает
        });
    }

}
