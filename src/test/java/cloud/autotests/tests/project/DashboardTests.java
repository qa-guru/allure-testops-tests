package cloud.autotests.tests.project;

import cloud.autotests.api.models.CreateProjectResponse;
import cloud.autotests.api.models.dashboard.DashboardRequest;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.helpers.extensions.annotations.CreateProject;
import cloud.autotests.tests.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static cloud.autotests.data.DashboardAction.DELETE_DASHBOARD;

@Owner("Denis")
@Story("Dashboard")
@DisplayName("Search project")
class DashboardTests extends TestBase {

    @Test
    @WithLogin
    @Tags({@Tag("web"), @Tag("dashboard")})
    @CreateProject(isPublic = true)
    @DisplayName("User is able to create a new dashboard in the project")
    void userAbleToCreateDashboard(CreateProjectResponse project) {
        final String ADD_WIDGET_NAME = "Add widget";
        final String dashboardName = new Faker().funnyName().name();

        projectPage.openPage(project.getId())
                .clickNewDashboard()
                .typeDashboardName(dashboardName)
                .clickSubmit()
                .verifyDashboardCreated(dashboardName, ADD_WIDGET_NAME);
    }

    @Test
    @WithLogin
    @Tags({@Tag("web"), @Tag("dashboard")})
    @CreateProject(isPublic = true)
    @DisplayName("User is able to remove an existent dashboard in the project")
    void userAbleToRemoveDashboard(CreateProjectResponse project) {
        final String dashboardName = new Faker().name().username();
        DashboardRequest request = new DashboardRequest(dashboardName, project.getId());
        dashboardService.createDashboard(request);

        projectPage.openPage(project.getId())
                .selectDashboard(request.getName())
                .clickMoreButton()
                .performAction(DELETE_DASHBOARD)
                .verifyDashboardRemoved(request.getName());
    }
}
