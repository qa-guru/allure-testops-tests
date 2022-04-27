package cloud.autotests.tests.project;

import cloud.autotests.api.project.CreateProjectRequestDto;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@Story("Project tests")
public class DeleteProjectTests extends BaseTest {

    private final CreateProjectRequestDto project = CreateProjectRequestDto.builder()
            .name("testuser-testproject-" + new Faker().random().hex(6))
            .isPublic(true)
            .build();
    private int projectId;

    @BeforeEach
    void signInAndCreateProject() {
        projectId = projectApi.createProject(project).getId();
        projectPage.openPage(projectId);
        projectPage.checkTitle(project.getName());
    }

    @Test
    @WithLogin
    @DisplayName("Delete project by UI")
    void deleteProjectByUI() {
        projectPage.getSidebar()
                .navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();

        projectsListPage.checkThatProjectsListDoNotContainsProject(project.getName());
    }

    @Test
    @WithLogin
    @DisplayName("Delete project by Api")
    void deleteProjectByApi() {
        // Act
        projectApi.deleteProject(projectId);
        projectsListPage.openPage();

        projectsListPage.checkThatProjectsListDoNotContainsProject(project.getName());
    }

}
