package cloud.autotests.tests;

import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsListPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Story("Project tests")
public class ProjectTests extends TestBase {

    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";

    @WithLogin
    @Test
    void projectPageShouldContains5Widgets() {
        //todo заменить, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);
        ProjectsListPage projectsListPage = new ProjectsListPage();
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectsTable projectsTable = new ProjectsTable();
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.checkThatWidgetsDisplayed();
    }

    @WithLogin
    @Test
    void testcaseListDisplayedAfterNavigateBySidebar() {
        //todo заменить, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);
        ProjectsListPage projectsListPage = new ProjectsListPage();
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectsTable projectsTable = new ProjectsTable();
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);
        TestCasesTable casesTable = new TestCasesTable();
        casesTable.shouldHaveSize(13);
    }
}
