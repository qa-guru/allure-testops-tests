package cloud.autotests.tests;

import cloud.autotests.api.dashboard.CreateDashboardRequestDto;
import cloud.autotests.api.dashboard.DashboardApi;
import cloud.autotests.helpers.WithLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DashboardTests extends TestBase {

    // Test project [dont-remove-autotests-for-dashboard]
    private final static int PROJECT_ID = 1175;
    private final CreateDashboardRequestDto dashboard = CreateDashboardRequestDto.builder()
            .name(faker.random().hex(15))
            .projectId(PROJECT_ID)
            .build();
    private Integer createdDashboardId;

    @AfterEach
    void deleteCreatedDashboard() {
        // Cleaning data
        // createdDashboardId != null, значит в рамках теста создан новый dashboard, который необходимо подчистить
        if (createdDashboardId != null)
            DashboardApi.deleteDashboard(createdDashboardId);
    }

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

        // For cleaning data in @AfterEach
        createdDashboardId = dashboardPage.getDashboardId();
    }

    @Test
    @WithLogin
    @DisplayName("Delete dashboard")
    void deleteDashboard() {
        // Arrange
        createdDashboardId = DashboardApi.createDashboard(dashboard).getId();
        dashboardPage.openPageDashboard(PROJECT_ID, createdDashboardId);
        dashboardPage.checkThatDashboardsListContainsDashboard(dashboard.getName());
        dashboardPage.checkThatNewDashboardHasAddWidgetButton();

        // Act
        dashboardPage.deleteDashboard();

        // Assert
        dashboardPage.checkThatDashboardsListDoNotContainsDashboard(dashboard.getName());

        // Присваиваю createdDashboardId = null,
        // тк удалять dashboard через API (в @AfterEach) не нужно (потому что мы его удалили через UI)
        createdDashboardId = null;
    }

}
