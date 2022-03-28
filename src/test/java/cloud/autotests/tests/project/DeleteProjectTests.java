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
import org.junit.jupiter.api.Test;

@Story("Project tests")
public class DeleteProjectTests extends TestBase {


    @WithLogin
    @Test
    void projectShouldBeDeletedByUi() {
        ProjectRequestBody requestBody = ProjectRequestBodyBuilder.builder()
                .addProjectName("testuser-testproject-toBeDeleted" + (new Faker()).random().hex(6))
                .addIsPublic(true)
                .build();

        Response createProjectResponse = ProjectApi.createProject(requestBody);
        Integer projectId = createProjectResponse.path("id");

        projectPage
                .openPage(projectId)
                .checkTitle(requestBody.getProjectName());

        // todo move to after fixture
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();

        // todo check test not appears in projects list results
    }

}
