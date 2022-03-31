package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Projects list tests")
public class ProjectsListTests extends TestBase {

    private final static String EXIST_PROJECT_NAME = "allure-testops-tests";

    @Test
    @WithLogin
    @DisplayName("Find exist project in projects list")
    void findExistProject() {
        // Arrange
        projectsListPage.openPage();

        // Act
        projectsListPage.findProject(EXIST_PROJECT_NAME);

        // Assert
        projectsListPage.checkThatProjectsListContainsProject(EXIST_PROJECT_NAME);
    }

}
