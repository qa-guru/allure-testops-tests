package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectsTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ProjectsListTests extends TestBase {

    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";

    @WithLogin
    @Test
    @DisplayName("User can search project on Projects list page")
    void userCanSearchProjectOnProjectsListPage() {
        ProjectsTable projectsTable = new ProjectsTable();

        projectsTable.openProjectsTablePage()
                .searchProject(PROJECT_NAME)
                .checkThatListOfProjectsContainsProject(PROJECT_NAME);
    }
}
