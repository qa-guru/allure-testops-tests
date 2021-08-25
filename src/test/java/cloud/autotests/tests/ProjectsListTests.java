package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectsTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ProjectsListTests extends TestBase {

    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";

    @WithLogin
    @Test
    @DisplayName("User can search project on Projects list page")
    void userCanSearchProjectOnProjectsListPage() {
        ProjectsTable projectsTable = new ProjectsTable();

        step("Open Projects list page", () ->
                open(""));

        step("Enter project name in Search field", () ->
                projectsTable.enterValueInSearchField(PROJECT_NAME));

        step("Check that project is displayed in search results", () ->
                projectsTable.checkThatListOfProjectsContainsProject(PROJECT_NAME));
    }
}
