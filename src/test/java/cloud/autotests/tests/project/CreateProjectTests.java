package cloud.autotests.tests.project;

import cloud.autotests.api.Project;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;

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
        String projectName = "testuser-testproject-toBeDeleted" +
                (new Faker()).random().hex(6);
        boolean isPublic = true;

        Response createProjectResponse = new Project().createProject(projectName, isPublic);
        Integer projectId = createProjectResponse.path("id");

        projectPage
                .openPage(projectId)
                .checkTitle(projectName);
    }

}
