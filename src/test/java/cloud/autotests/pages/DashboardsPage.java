package cloud.autotests.pages;

import cloud.autotests.data.dashboards.DashboardActionItem;
import cloud.autotests.data.dashboards.WidgetActionItem;
import cloud.autotests.pages.components.Alerts;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

public class DashboardsPage {

    Alerts alert = new Alerts();

    //dashboards page header elements
    private ElementsCollection dashboardsList = $(".ProjectDashboards__tabs").$$(".Tabs__item");
    private SelenideElement dashboardTitle = $(".BreadCrumbs");
    private SelenideElement newDashboardButton = $(".ProjectDashboards__title .Button");
    private SelenideElement dashboardActionsButton = $(".ProjectDashboards__navigation .Button");
    private SelenideElement fullScreenControls = $(".Dashboard__fullscreen-controls");

    //dashboard content elements
    private ElementsCollection widgetsList = $(".Dashboard__content").$$(".react-grid-item");
    private SelenideElement addWidgetButton = $(".Dashboard__empty .Button");

    //region Get elements methods
    public SelenideElement getDashboardTab(String dashboardName) {
        return dashboardsList.find(text(dashboardName));
    }

    public SelenideElement getDashboardActionItem(DashboardActionItem actionItem) {
        return $$(".Menu__item").find(text(actionItem.getDisplayedName()));
    }

    public SelenideElement getExitFullScreenButton() {
        return fullScreenControls.hover().$("[type=button]");
    }

    public SelenideElement getWidgetByName(String widgetName) {
        return widgetsList.find(text(widgetName));
    }

    private SelenideElement getWidgetActionsButton(String widgetName) {
        return getWidgetByName(widgetName).$(".Widget__header").$(".Button");
    }

    public SelenideElement getWidgetActionItem(WidgetActionItem actionItem) {
        return $$(".Menu__item").find(text(actionItem.getDisplayedName()));
    }
    //endregion

    //region Alerts steps
    @Step("Close toastify")
    public DashboardsPage closeToastify() {
        alert.getToastifyCloseButton().click();
        return this;
    }

    @Step("Confirm alert")
    public DashboardsPage confirmAlert() {
        alert.confirmAlert();
        return this;
    }

    @Step("Dismiss alert")
    public DashboardsPage dismissAlert() {
        alert.dismissAlert();
        return this;
    }
    //endregion

    //region Dashboards page header steps
    @Step("Open dashboard page")
    public DashboardsPage openDashboardPage(String dashboardUrl) {
        open("/favicon.ico");
        localStorage().setItem("AS_AUTH_2", System.getProperty("AUTH_2"));
        open(dashboardUrl);
        return this;
    }

    @Step("'New dashboard' button click")
    public DashboardsPage clickNewDashboardButton() {
        newDashboardButton.click();
        return this;
    }

    @Step("Select dashboard '{dashboardName}'")
    public DashboardsPage selectDashboard(String dashboardName) {
        getDashboardTab(dashboardName).click();
        return this;
    }

    @Step("Select dashboard action '{actionItem}'")
    public DashboardsPage selectDashboardAction(DashboardActionItem actionItem) {
        dashboardActionsButton.click();
        getDashboardActionItem(actionItem).click();
        return this;
    }

    @Step("Check that dashboard '{dashboardName}' exist")
    public DashboardsPage checkThatDashboardExist(String dashboardName) {
        getDashboardTab(dashboardName).shouldBe(visible);
        return this;
    }

    @Step("Check that dashboard '{dashboardName}' deleted")
    public DashboardsPage checkThatDashboardDeleted(String dashboardName) {
        getDashboardTab(dashboardName).shouldNotBe(visible);
        return this;
    }

    @Step("Exit full-screen mode")
    public DashboardsPage exitFullScreenMode() {
        getExitFullScreenButton().click();
        return this;
    }

    @Step("Check that full screen mode is on")
    public DashboardsPage checkFullScreenModeIsOn() {
        getExitFullScreenButton().should(visible);
        return this;
    }

    @Step("Check that full screen mode is off")
    public DashboardsPage checkFullScreenModeIsOff() {
        fullScreenControls.shouldNot(exist);
        return this;
    }
    //endregion

    //region Default dashboard widgets steps
    @Step("Check that all widgets on overview tab are displayed")
    public DashboardsPage checkThatWidgetsDisplayed() {
        getWidgetByName("Test cases").should(visible);
        getWidgetByName("Automation").should(visible);
        getWidgetByName("Launches").should(visible);
        getWidgetByName("Automation trend").should(visible);
        getWidgetByName("Mute trend").should(visible);
        return this;
    }
    //endregion

    //region Custom dashboard widgets steps
    @Step("'Add widget' button click")
    public DashboardsPage addWidgetButtonClick() {
        addWidgetButton.click();
        return this;
    }

    @Step("Select widget action '{actionItem}'")
    public DashboardsPage selectWidgetAction(String widgetName, WidgetActionItem actionItem) {
        getWidgetActionsButton(widgetName).click();
        getWidgetActionItem(actionItem).click();
        return this;
    }

    @Step("Check that widget '{widgetName}' exist")
    public DashboardsPage checkThatWidgetExist(String widgetName) {
        getWidgetByName(widgetName).should(visible);
        return this;
    }

    @Step("Check that widget '{widgetName}' deleted")
    public DashboardsPage checkThatWidgetDeleted(String widgetName) {
        getWidgetByName(widgetName).shouldNot(visible);
        return this;
    }

    @Step("Check that widget have 'Markdown Articles' content")
    public DashboardsPage checkWidgetHaveMarkdownArticles(String widgetName) {
        getWidgetByName(widgetName).$(".MarkdownArticle").shouldHave(text(widgetName));
        return this;
    }

    @Step("Check that widget have 'Trend chart graph' content")
    public DashboardsPage checkWidgetHaveTrendChartGraph(String widgetName) {
        getWidgetByName(widgetName).$(".TrendChart__graph").should(visible);
        return this;
    }

    @Step("Check that widget have 'Pie chart graph' content")
    public DashboardsPage checkWidgetHavePieChartGraph(String widgetName) {
        getWidgetByName(widgetName).$(".PieChart__graph").should(visible);
        return this;
    }

    @Step("Check that widget have 'Tree view chart graph' content")
    public DashboardsPage checkWidgetHaveTreeViewChartGraph(String widgetName) {
        getWidgetByName(widgetName).$(".TreeViewChart__graph").should(visible);
        return this;
    }

    @Step("Check that widget have 'Test case row list' content")
    public DashboardsPage checkWidgetHaveTestCaseRowList(String widgetName) {
        getWidgetByName(widgetName).$(".list").should(visible);
        return this;
    }

    @Step("Check that widget have 'Launch row list' content")
    public DashboardsPage checkWidgetHaveLaunchRowList(String widgetName) {
        getWidgetByName(widgetName).$(".list").should(visible);
        return this;
    }
    //endregion
}