package cloud.autotests.tests.project;

import cloud.autotests.api.project.ProjectApi;
import cloud.autotests.api.project.ProjectRequestBody;
import cloud.autotests.api.project.ProjectRequestBodyBuilder;
import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
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
    private final ProjectRequestBody requestBody = ProjectRequestBodyBuilder.builder()
            .addProjectName(projectName)
            .addIsPublic(true)
            .build();

    private Integer projectId;

    @BeforeEach
    void signInAndCreateProject() {
        // ToDo заменить на @WithLogin
        // Arrange
        loginPage.open();
        loginPage.signIn(App.config.userLogin(), App.config.userPassword());

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
    @DisplayName("Delete project by UI")
    void deleteProjectByUI() {
        // Act
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();
    }

    @Test
    @DisplayName("Delete project by Api")
    void deleteProjectByApi() {
        // Act
        ProjectApi.removeProject(projectId);
    }

}
