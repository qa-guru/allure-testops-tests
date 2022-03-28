package cloud.autotests.tests.project;

import cloud.autotests.api.project.ProjectApi;
import cloud.autotests.api.project.ProjectRequestBody;
import cloud.autotests.api.project.ProjectRequestBodyBuilder;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

@Story("Project tests")
public class CreateProjectTests extends TestBase {

    @AfterEach
    void deleteProject() {
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();
    }

    @WithLogin
    @Test
    void projectShouldBeCreatedByUi() {
        String projectName = "testuser-testproject-toBeDeleted" +
                (new Faker()).random().hex(6);

        projectsListPage
                .openPage()
                .createNewProject(projectName);
        projectPage.checkTitle(projectName);
    }

    @WithLogin
    @Test
    void projectShouldBeCreatedByApi() {
        ProjectRequestBody requestBody = ProjectRequestBodyBuilder.builder()
                .addProjectName("testuser-testproject-toBeDeleted" + (new Faker()).random().hex(6))
                .addIsPublic(true)
                .build();

        Response createProjectResponse = ProjectApi.createProject(requestBody);
        Integer projectId = createProjectResponse.path("id");

        projectPage
                .openPage(projectId)
                .checkTitle(requestBody.getProjectName());
    }

}
