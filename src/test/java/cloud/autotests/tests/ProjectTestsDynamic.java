package cloud.autotests.tests;

import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectsListPage;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Story("Projects tests dynamic interactions")
public class ProjectTestsDynamic extends TestBase {

    @WithLogin
    @Test
    @Disabled("Cut to several tests")
    void deleteProjectCreatedInThisTest() {
        //предусловия
        String projectName = "testuser-testproject-toBeDeleted" + (new Faker()).random().hex(6);
        ProjectsListPage projectsListPage = new ProjectsListPage();
        projectsListPage.openPage();
        ProjectPage projectPage = projectsListPage.createNewProject(projectName);

        //тест
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();
        projectsListPage.filterProject(projectName);

        //проверка
        ProjectsTable projectsTable = projectsListPage.getProjectsTable();
        SoftAssertions softly = new SoftAssertions(); //чтобы выставить тайминг в проверке остановился на 500 мс иначе тест периодически падает
        softly.assertThat(projectsTable.getProjectTableSize()).isEqualTo(0);
        softly.assertAll();

    }
}
