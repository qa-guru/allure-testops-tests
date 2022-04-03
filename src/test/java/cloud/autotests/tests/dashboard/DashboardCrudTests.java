package cloud.autotests.tests.dashboard;

import cloud.autotests.api.dashboard.CreateDashboardRequestDto;
import cloud.autotests.api.dashboard.DashboardApi;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.dashboard.DashboardPage;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Dashboard tests")
public class DashboardCrudTests extends BaseTest {

    private final DashboardPage dashboardPage = new DashboardPage();

    // Test project [dont-remove-autotests-for-dashboard]
    private final static int PROJECT_ID = 1175;
    private final CreateDashboardRequestDto dashboard = CreateDashboardRequestDto.builder()
            .name(faker.random().hex(15))
            .projectId(PROJECT_ID)
            .build();

    @Test
    @WithLogin
    @DisplayName("Create dashboard")
    void createDashboard() {
        // Arrange
        dashboardPage.openPageOverview(PROJECT_ID);

        // Act
        dashboardPage.createNewDashboard(dashboard.getName());

        // Assert
        dashboardPage.checkThatDashboardsListContainsDashboard(dashboard.getName());
        dashboardPage.checkThatNewDashboardHasAddWidgetButton();

        // Cleaning data
        int createdDashboardId = dashboardPage.getDashboardId();
        DashboardApi.deleteDashboard(createdDashboardId);
    }

    @Test
    @WithLogin
    @DisplayName("Edit dashboard")
    @Disabled
    void editDashboard() {}

    @Test
    @WithLogin
    @DisplayName("Delete dashboard")
    void deleteDashboard() {
        // Arrange
        int createdDashboardId = DashboardApi.createDashboard(dashboard).getId();
        dashboardPage.openPageDashboard(PROJECT_ID, createdDashboardId);
        dashboardPage.checkThatDashboardsListContainsDashboard(dashboard.getName());
        dashboardPage.checkThatNewDashboardHasAddWidgetButton();

        // Act
        dashboardPage.deleteDashboard();

        // Assert
        dashboardPage.checkThatDashboardsListDoNotContainsDashboard(dashboard.getName());
    }

}
