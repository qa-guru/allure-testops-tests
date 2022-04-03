package cloud.autotests.tests.project;

import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Story("Project tests")
public class ProjectTests extends BaseTest {

    static Stream<Arguments> findExistProjectProvider(){
        return Stream.of(
                Arguments.of("public project", "dont-remove-autotests-public-project"),
                Arguments.of("non-public project", "dont-remove-autotests-non-public-project")
        );
    }

    @WithLogin
    @MethodSource("findExistProjectProvider")
    @ParameterizedTest(name = "Find exist {0} [{1}] in projects list")
    void findExistProject(String projectPrivacy, String projectName) {
        // Arrange
        projectsListPage.openPage();

        // Act
        projectsListPage.findProject(projectName);

        // Assert
        projectsListPage.checkThatProjectsListContainsProject(projectName);
    }

    @Test
    @WithLogin
    @DisplayName("Find don't exist project in projects list")
    void findDoNotExistProject() {
        // Test data
        String doNotExistProject = "OneTwoThreeLaLaLaLa123456789";

        // Arrange
        projectsListPage.openPage();

        // Act
        projectsListPage.findProject(doNotExistProject);

        // Assert
        projectsListPage.checkThatProjectsListDoNotContainsProject(doNotExistProject);
    }

    @Test
    @WithLogin
    @DisplayName("Project display all widgets")
    void projectDisplayAllWidgets() {
        // Test data
        // Project [dont-remove-autotests-for-project]
        int projectId = 1191;

        // Act
        projectPage.openPage(projectId);

        // Assert
        projectPage.checkThatProjectHas5Widgets();
    }

}
