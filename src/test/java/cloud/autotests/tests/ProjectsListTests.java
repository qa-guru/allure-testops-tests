package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectsTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
