package cloud.autotests.tests.dashboards;

import cloud.autotests.config.App;
import cloud.autotests.data.dashboards.DashboardActionItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.DashboardsPage;
import cloud.autotests.pages.components.forms.dashboards.FormEditDashboard;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("Dashboards tests")
public class DashboardsTests extends TestBase {

    DashboardsPage dashboardsPage = new DashboardsPage();
    FormEditDashboard formEditDashboard = new FormEditDashboard();
    String dashboardsUrl = App.config.webUrl() + "/project/308/dashboards";

    //region Add new dashboards tests
    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Add new dashboard")
    void addDashboard() {
        String dashboardName = "NewDashboard";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(dashboardName)
                .checkThatDashboardExist(dashboardName)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Add dashboard with blank name")
    void addDashboardWithBlankName() {
        String dashboardName = "";
        open(dashboardsUrl);
        dashboardsPage.clickNewDashboardButton();
        formEditDashboard.setNameInput(dashboardName)
                .clickSubmitButton()
                .checkThatNameErrorMessageIs("Name is required");
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Add dashboard with only space characters in name")
    void addDashboardWithSpacesName() {
        String dashboardName = "   ";
        open(dashboardsUrl);
        dashboardsPage.clickNewDashboardButton();
        formEditDashboard.setNameInput(dashboardName)
                .clickSubmitButton()
                .checkThatNameErrorMessageIs("Name should not be blank");
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Cancel adding new dashboard using cancel button")
    void cancelAddingDashboardByCancelButton() {
        String dashboardName = "CancelButton";
        open(dashboardsUrl);
        dashboardsPage.clickNewDashboardButton();
        formEditDashboard.setNameInput(dashboardName)
                .clickCancelButton();
        dashboardsPage.confirmAlert();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Cancel adding new dashboard using close button")
    void cancelAddingDashboardByCloseButton() {
        String dashboardName = "CloseButton";
        open(dashboardsUrl);
        dashboardsPage.clickNewDashboardButton();
        formEditDashboard.setNameInput(dashboardName)
                .clickCloseButton();
        dashboardsPage.confirmAlert();
    }


    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Cancel adding new dashboard with blank name using cancel button")
    void cancelAddingDashboardWithBlankNameByCancelButton() {
        String dashboardName = "";
        open(dashboardsUrl);
        dashboardsPage.clickNewDashboardButton();
        formEditDashboard.setNameInput(dashboardName)
                .clickCancelButton()
                .checkThatFormIsClosed();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Add new dashboard tests")
    @DisplayName("Cancel adding new dashboard with blank name using close button")
    void cancelAddingDashboardWithBlankNameByCloseButton() {
        String dashboardName = "";
        open(dashboardsUrl);
        dashboardsPage.clickNewDashboardButton();
        formEditDashboard.setNameInput(dashboardName)
                .clickCloseButton()
                .checkThatFormIsClosed();
    }
    //endregion

    //region Various dashboard tests
    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various dashboard tests")
    @DisplayName("Switch dashboard full screen mode on/off")
    void dashboardFullScreenMode() {
        String dashboardName = "FullScreen";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(dashboardName)
                .selectDashboardAction(DashboardActionItem.FULLSCREEN_MODE)
                .checkFullScreenModeIsOn()
                .exitFullScreenMode()
                .checkFullScreenModeIsOff()
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various dashboard tests")
    @DisplayName("Edit dashboard name")
    void editDashboardName() {
        String dashboardName = "Edit";
        String newDashboardName = "EditNew";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(dashboardName)
                .selectDashboardAction(DashboardActionItem.EDIT_DASHBOARD);
        formEditDashboard.setNameInput(newDashboardName)
                .clickSubmitButton();
        dashboardsPage.checkThatDashboardExist(newDashboardName)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various dashboard tests")
    @DisplayName("Delete dashboard")
    void deleteDashboard() {
        String dashboardName = "Delete";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(dashboardName)
                .deleteDashboard()
                .checkThatDashboardDeleted(dashboardName);
    }
    //endregion
}
