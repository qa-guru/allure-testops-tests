package cloud.autotests.tests.project;

import cloud.autotests.api.Project;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;

@Story("Project tests")
public class DeleteProjectTests extends TestBase {


    @WithLogin
    @Test
    void projectShouldBeDeletedByUi() {
        String projectName = "testuser-testproject-toBeDeleted" +
                (new Faker()).random().hex(6);
        boolean isPublic = true;

        Response createProjectResponse = new Project().createProject(projectName, isPublic);
        Integer projectId = createProjectResponse.path("id");

        projectPage
                .openPage(projectId)
                .checkTitle(projectName);

        // todo move to after fixture
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();

        // todo check test not appears in projects list results
    }

}
