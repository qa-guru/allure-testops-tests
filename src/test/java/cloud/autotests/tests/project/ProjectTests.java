package cloud.autotests.tests.project;

import cloud.autotests.api.project.ProjectResponseDto;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.helpers.extensions.WithProject;
import cloud.autotests.tests.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Story("Project tests")
class ProjectTests extends BaseTest {

    @Test
    @WithLogin
    @WithProject(isPublic = true)
    @Tags({@Tag("web"), @Tag("search")})
    @DisplayName("Find project by the project name with visibility true")
    void findExistProject(ProjectResponseDto project) {
        projectsListPage.openPage();
        projectsListPage.findProject(project.getName());
        projectsListPage.checkThatProjectsListContainsProject(project.getName());
    }

    @Test
    @WithLogin
    @WithProject(isPublic = false)
    @Tags({@Tag("web"), @Tag("search")})
    @DisplayName("Find project by the project name with visibility false")
    void findProjectByNameWithVisibilityFalse(ProjectResponseDto project) {
        projectsListPage.openPage();
        projectsListPage.findProject(project.getName());
        projectsListPage.checkThatProjectsListContainsProject(project.getName());
    }

    @Test
    @WithLogin
    @Tags({@Tag("web"), @Tag("search")})
    @DisplayName("Find project by none existent project name")
    void findProjectByNoneExistentName() {
        final String projectName = new Faker().funnyName().name();

        projectsListPage.openPage();
        projectsListPage.findProject(projectName);
        projectsListPage.checkThatProjectsListDoNotContainsProject(projectName);
    }

    @Test
    @WithLogin
    @WithProject(isPublic = true)
    @DisplayName("Project display all widgets")
    void projectDisplayAllWidgets(ProjectResponseDto project) {
        projectPage.openPage(project.getId());
        projectPage.checkThatProjectHas5Widgets();
    }

}
