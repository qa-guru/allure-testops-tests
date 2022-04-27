package cloud.autotests.tests.project;

import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Story("Project tests")
public class ProjectTests extends BaseTest {

    @WithLogin
    @CsvSource(value = {
            "public project,dont-remove-autotests-public-project",
            "non-public project,dont-remove-autotests-non-public-project"
    })
    @ParameterizedTest(name = "Find exist {0} [{1}] in projects list")
    void findExistProject(String projectPrivacy, String projectName) {
        projectsListPage.openPage();
        projectsListPage.findProject(projectName);

        projectsListPage.checkThatProjectsListContainsProject(projectName);
    }

    @Test
    @WithLogin
    @DisplayName("Find don't exist project in projects list")
    void findDoNotExistProject() {
        String doNotExistProject = "OneTwoThreeLaLaLaLa123456789";

        projectsListPage.openPage();
        projectsListPage.findProject(doNotExistProject);

        projectsListPage.checkThatProjectsListDoNotContainsProject(doNotExistProject);
    }

    @Test
    @WithLogin
    @DisplayName("Project display all widgets")
    void projectDisplayAllWidgets() {
        // Project [dont-remove-autotests-for-project]
        int projectId = 1191;

        projectPage.openPage(projectId);
        projectPage.checkThatProjectHas5Widgets();
    }

}
