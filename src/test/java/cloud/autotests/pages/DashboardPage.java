package cloud.autotests.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private final ElementsCollection dashboardTabs = $$(".ProjectDashboards__navigation .Tabs__text");

    @Step("Open dashboard page by dashboardId [{dashboardId}] on projectId [{projectId}]")
    public void openPageDashboard(int projectId, int dashboardId) {
        open("/project/" + projectId + "/dashboards/" + dashboardId);
    }

    @Step("Open dashboard page [Overview] by projectId [{projectId}]")
    public void openPageOverview(int projectId) {
        open("/project/" + projectId + "/dashboards");
    }

    @Step("Get (parse) dashboard id")
    public int getDashboardId() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String[] splitUrl = currentUrl.split("/");
        String idString = splitUrl[splitUrl.length - 1];
        return Integer.parseInt(idString);
    }

    @Step("Add new dashboard with name [{dashboardName}]")
    public void createNewDashboard(String dashboardName) {
        $(".ProjectDashboards__title button").click();
        $(byName("name")).setValue(dashboardName);
        $(byName("submit")).click();
    }

    @Step("Delete dashboard")
    public void deleteDashboard() {
        $("button.Button_shape_round").click();
        $x("//*[@*='#delete']").closest("div").click();
        $("button.Button_style_danger").click();
    }

    @Step("Verify dashboard [{dashboardName}] contains in dashboards list")
    public void checkThatDashboardsListContainsDashboard(String dashboardName) {
        dashboardTabs.shouldHave(CollectionCondition.itemWithText(dashboardName));
    }

    @Step("Verify dashboard [{dashboardName}] don't contains in dashboards list")
    public void checkThatDashboardsListDoNotContainsDashboard(String dashboardName) {
        dashboardTabs.find(text(dashboardName)).shouldNotBe(visible);
    }

    @Step("Verify new dashboard has [Add widget] button")
    public void checkThatNewDashboardHasAddWidgetButton() {
        $$("button").find(text("Add widget")).shouldBe(visible);
    }

}
