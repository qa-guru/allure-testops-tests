package cloud.autotests.tests.project;

import cloud.autotests.api.project.CreateProjectRequestDto;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@Story("Project tests")
public class CreateProjectTests extends BaseTest {

    private final CreateProjectRequestDto project = CreateProjectRequestDto.builder()
            .name("testuser-testproject-" + faker.random().hex(6))
            .isPublic(true)
            .build();

    @Test
    @WithLogin
    @DisplayName("Create new project by UI")
    void createNewProjectByUI() {
        projectsListPage.openPage();

        projectsListPage.createNewProject(project.getName());
        int projectId = projectApi.getProjectsByName(project.getName())[0].getId();
        projectPage.checkTitle(project.getName());

        projectApi.deleteProject(projectId);
    }

    @Test
    @WithLogin
    @DisplayName("Create new project with empty value of name field")
    void createNewProjectWithEmptyName() {
        projectsListPage.openPage();

        projectsListPage.createNewProject("");
        projectsListPage.createProjectPopup
                .checkThatEmptyNameFieldHasErrorMessage();
    }

    @Test
    @WithLogin
    @DisplayName("Create new project by API")
    void createProjectByApi() {
        int projectId = projectApi.createProject(this.project).getId();
        projectPage.openPage(projectId);

        projectPage.checkTitle(project.getName());

        projectApi.deleteProject(projectId);
    }

}
