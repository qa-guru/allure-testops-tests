package cloud.autotests.tests.project;

import cloud.autotests.api.project.CreateProjectRequestDto;
import cloud.autotests.api.project.ProjectApi;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@Story("Project tests")
public class CreateProjectTests extends TestBase {

    private final CreateProjectRequestDto project = CreateProjectRequestDto.builder()
            .name("testuser-testproject-" + faker.random().hex(6))
            .isPublic(true)
            .build();

    @Test
    @WithLogin
    @DisplayName("Create new project by UI")
    void createNewProjectByUI() {
        // Arrange
        projectsListPage.openPage();

        // Act
        projectsListPage.createNewProject(project.getName());
        int projectId = ProjectApi.getProjectsByName(project.getName())[0].getId();

        // Assert
        projectPage.checkTitle(project.getName());

        // Cleaning data
        ProjectApi.deleteProject(projectId);
    }

    @Test
    @WithLogin
    @DisplayName("Create new project with empty value of name field")
    void createNewProjectWithEmptyName() {
        // Arrange
        projectsListPage.openPage();

        // Act
        projectsListPage.createNewProject("");

        // Assert
        projectsListPage.createProjectPopup
                .checkThatEmptyNameFieldHasErrorMessage();
    }

    @Test
    @WithLogin
    @DisplayName("Create new project by API")
    void createProjectByApi() {
        // Act
        int projectId = ProjectApi.createProject(this.project).getId();
        projectPage.openPage(projectId);

        // Assert
        projectPage.checkTitle(project.getName());

        // Cleaning data
        ProjectApi.deleteProject(projectId);
    }

}
