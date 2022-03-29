package cloud.autotests.tests.project;

import cloud.autotests.api.project.ProjectApi;
import cloud.autotests.api.project.ProjectRequestBody;
import cloud.autotests.api.project.ProjectRequestBodyBuilder;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@Story("Project tests")
@Feature("Create project")
public class CreateProjectTests extends TestBase {

    private final String projectName = "testuser-testproject-" + faker.random().hex(6);
    private Integer projectId;

    @AfterEach
    void removeProject() {
        // Cleaning data
        // projectId != null, значит в рамках теста был создан новый проект, который нужно подчистить
        if (projectId != null)
            ProjectApi.removeProject(projectId);
    }

    @Test
    @WithLogin
    @DisplayName("Create new project by UI")
    void createNewProjectByUI() {
        // Arrange
        projectsListPage.openPage();

        // Act
        projectsListPage.createNewProject(projectName);
        projectId = ProjectApi.getProjectId(projectName);

        // Assert
        projectPage.checkTitle(projectName);
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
        // Test data
        ProjectRequestBody requestBody = ProjectRequestBodyBuilder.builder()
                .addProjectName(projectName)
                .addIsPublic(true)
                .build();

        // Act
        projectId = ProjectApi.createProjectAndGetId(requestBody);
        projectPage.openPage(projectId);

        // Assert
        projectPage.checkTitle(requestBody.getProjectName());
    }

}
