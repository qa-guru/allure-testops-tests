package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Story("Projects list tests")
public class ProjectsListTests extends TestBase {

    static Stream<Arguments> findExistProjectProvider(){
        return Stream.of(
                Arguments.of("public project", "dont-remove-autotests-public-project"),
                Arguments.of("non-public project", "dont-remove-autotests-non-public-project")
        );
    }

    @BeforeEach
    void openProjectsListPage() {
        projectsListPage.openPage();
    }

    @WithLogin
    @MethodSource("findExistProjectProvider")
    @ParameterizedTest(name = "Find exist {0} [{1}] in projects list")
    void findExistProject(String projectPrivacy, String projectName) {
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

        // Act
        projectsListPage.findProject(doNotExistProject);

        // Assert
        projectsListPage.checkThatProjectsListDoNotContainsProject(doNotExistProject);
    }

}
