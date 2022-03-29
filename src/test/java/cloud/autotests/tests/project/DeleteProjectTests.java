package cloud.autotests.tests.project;

import cloud.autotests.api.project.ProjectApi;
import cloud.autotests.api.project.ProjectDto;
import cloud.autotests.api.project.ProjectDtoBuilder;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Story("Project tests")
@Feature("Delete project")
public class DeleteProjectTests extends TestBase {

    private final String projectName = "testuser-testproject-" + new Faker().random().hex(6);
    private final ProjectDto requestBody = ProjectDtoBuilder.builder()
            .setProjectName(projectName)
            .setIsPublic(true)
            .build();
    private Integer projectId;

    @BeforeEach
    void signInAndCreateProject() {
        projectId = ProjectApi.createProjectAndGetId(requestBody);
        projectPage.openPage(projectId);
        projectPage.checkTitle(requestBody.getProjectName());
    }

    @AfterEach
    void checkThatProjectIsRemoved() {
        // Assert
        // Api-запрос возвращает null в том случае,
        // если проекта [projectName] не существует
        assertNull(ProjectApi.getProjectId(projectName));
    }

    @Test
    @WithLogin
    @DisplayName("Delete project by UI")
    void deleteProjectByUI() {
        // Act
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();
    }

    @Test
    @WithLogin
    @DisplayName("Delete project by Api")
    void deleteProjectByApi() {
        // Act
        ProjectApi.removeProject(projectId);
    }

}
