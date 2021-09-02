package cloud.autotests.tests.dashboards;

import cloud.autotests.config.App;
import cloud.autotests.data.dashboards.*;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.DashboardsPage;
import cloud.autotests.pages.components.forms.dashboards.FormEditWidget;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Selenide.open;

@Feature("Dashboard widgets tests")
public class WidgetsTests extends TestBase {

    DashboardsPage dashboardsPage = new DashboardsPage();
    FormEditWidget formEditWidget = new FormEditWidget();
    String dashboardsUrl = App.config.webUrl() + "/project/331/dashboards";

    //region Standard (Overview) dashboard widgets tests
    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Standard (Overview) dashboard widgets tests")
    @DisplayName("Standard dashboard should contains 5 widgets")
    void defaultDashboardShouldContains5Widgets() {
        open(dashboardsUrl);
        dashboardsPage.checkThatWidgetsDisplayed();
    }
    //endregion

    //region Various widget tests
    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various widget tests")
    @DisplayName("Adding widget using 'Add widget' button")
    void addWidgetByAddWidgetButton() {
        String name = "ByAddWidgetButton";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .addWidgetButtonClick();
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkThatWidgetExist(name)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various widget tests")
    @DisplayName("Editing widget")
    void editWidget() {
        String name = "EditWidget";
        String newName = name + "New";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .addWidgetButtonClick();
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .selectWidgetAction(name, WidgetActionItem.EDIT);
        formEditWidget.setNameInput(newName)
                .selectTypeItem(FormTypeItem.LAUNCH_STATISTIC_TREND)
                .clickSubmitButton();
        dashboardsPage.checkThatWidgetExist(newName)
                .checkWidgetHaveTrendChartGraph(newName)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various widget tests")
    @DisplayName("Cloning widget")
    void cloneWidget() {
        String name = "CloneWidget";
        String newName = name + "New";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .addWidgetButtonClick();
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .selectWidgetAction(name, WidgetActionItem.CLONE);
        formEditWidget.setNameInput(newName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.checkThatWidgetExist(newName)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Various widget tests")
    @DisplayName("Deleting widget")
    void deleteWidget() {
        String name = "DeleteWidget";
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .addWidgetButtonClick();
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .selectWidgetAction(name, WidgetActionItem.DELETE);
        dashboardsPage.checkThatWidgetDeleted(name)
                .deleteDashboard();
    }
    //endregion

    //region Adding some types of widgets
    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Adding some types of widgets")
    @DisplayName("Adding widget with type 'Markdown'")
    void addMarkdownTypeWidget() {
        String name = FormTypeItem.MARKDOWN.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.MARKDOWN)
                .fillContentWriteTextArea(name)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveMarkdownArticles(name)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Adding some types of widgets")
    @DisplayName("Adding widget with type 'Launch statistic Trend'")
    void addLaunchStatisticTrendTypeWidget() {
        String name = FormTypeItem.LAUNCH_STATISTIC_TREND.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCH_STATISTIC_TREND)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveTrendChartGraph(name)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Adding some types of widgets")
    @DisplayName("Adding widget with type 'Automation Trend'")
    void addAutomationTrendTypeWidget() {
        String name = FormTypeItem.AUTOMATION_TREND.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.AUTOMATION_TREND)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveTrendChartGraph(name)
                .deleteDashboard();
    }

    @WithLogin
    @Test
    @Owner("Oleg1717")
    @Story("Adding some types of widgets")
    @DisplayName("Adding widget with type 'Launches'")
    void addLaunchesTypeWidget() {
        String name = FormTypeItem.LAUNCHES.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveLaunchRowList(name)
                .deleteDashboard();
    }

    @WithLogin
    @Owner("Oleg1717")
    @Story("Adding widgets with type 'Launch Analytics'")
    @ParameterizedTest(name = "Adding widget with: 'Metric' item = {0}")
    @EnumSource(value = FormLaunchAnalyticMetricItem.class)
    void addLaunchAnalyticsWidgets(FormLaunchAnalyticMetricItem item) {
        String name = item.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.LAUNCH_ANALYTICS)
                .selectLaunchAnalyticMetricItem(item)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveTrendChartGraph(name)
                .deleteDashboard();
    }

    @WithLogin
    @Owner("Oleg1717")
    @Story("Adding widgets with type 'Test Case Pie Chart'")
    @ParameterizedTest(name = "Adding widget with: 'Group by' item = {0}")
    @EnumSource(value = FormGroupByItem.class)
    void addTestCasePieChartWidgets(FormGroupByItem item) {
        String name = item.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.TEST_CASE_PIE_CHART)
                .selectGroupByItem(item)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHavePieChartGraph(name)
                .deleteDashboard();
    }

    @WithLogin
    @Owner("Oleg1717")
    @Story("Adding widgets with type 'Top Test Cases'")
    @ParameterizedTest(name = "Adding widget with: 'Metric' item = {0}")
    @EnumSource(value = FormTopTestCasesMetricItem.class)
    void addTopTestCasesWidgets(FormTopTestCasesMetricItem item) {
        String name = item.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.TOP_TEST_CASES)
                .selectTopTestCasesMetricItem(item)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveTestCaseRowList(name)
                .deleteDashboard();
    }

    @WithLogin
    @Owner("Oleg1717")
    @Story("Adding widgets with type 'Test Case Tree Map Chart'")
    @ParameterizedTest(name = "Adding widget with: 'Tree' item = {0}")
    @EnumSource(value = FormTreeItem.class)
    void addTestCaseTreeMapChartWidgets(FormTreeItem item) {
        String name = item.toString();
        open(dashboardsUrl);
        dashboardsPage.addNewDashboard(name)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        formEditWidget.setNameInput(name)
                .selectTypeItem(FormTypeItem.TEST_CASE_TREE_MAP_CHART)
                .selectTreeItem(item)
                .clickSubmitButton();
        dashboardsPage.closeToastify()
                .checkWidgetHaveTreeViewChartGraph(name)
                .deleteDashboard();
    }
    //endregion
}
