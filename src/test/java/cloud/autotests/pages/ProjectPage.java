package cloud.autotests.pages;

import cloud.autotests.data.DashboardAction;
import cloud.autotests.pages.components.Sidebar;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class ProjectPage {

    private Sidebar sidebar = new Sidebar();

    private ElementsCollection widgets = $$(".WidgetOld");

    private SelenideElement testCasesWidget = widgets.find(text("Test cases"));
    private SelenideElement automationWidget = widgets.find(text("Automation"));
    private SelenideElement launchesWidget = widgets.find(text("Launches"));
    private SelenideElement automationTrendWidget = widgets.find(text("Automation trend"));
    private SelenideElement muteTrendWidget = widgets.find(text("Mute trend"));
    private SelenideElement newDashboardBtn = $(".ProjectDashboards__title > .Button_shape_rectangular");
    private ElementsCollection listOfDashboards = $$(".ProjectDashboards__tabs .Tabs__text");
    private ElementsCollection listOfActions = $$(".tippy-content > div > div");


    @Step("Open project by id `{projectId}`")
    public ProjectPage openPage(Integer projectId) {
        open("/project/" + projectId);
        return this;
    }

    @Step("Get Sidebar")
    public Sidebar getSidebar() {
        return sidebar;
    }

    @Step("Check that all widgets are displayed")
    public void checkThatWidgetsDisplayed() {
        testCasesWidget.shouldBe(visible);
        automationWidget.shouldBe(visible);
        launchesWidget.shouldBe(visible);
        automationTrendWidget.shouldBe(visible);
        muteTrendWidget.shouldBe(visible);
    }

    @Step("Delete project: Push 'delete' button and confirm a deletion")
    public void deleteProject() {
        $$("button.Button_style_danger").find(text("Delete project")).click();
        $$("button.Button_style_danger").find(exactText("Delete")).click();
    }

    @Step("Check title has text `{title}`")
    public void checkTitle(String title) {
        $(".ProjectDashboards__title a").shouldHave(text(title));
    }

    @Step("Click on the 'New Dashboard' button")
    public ProjectPage clickNewDashboard() {
        newDashboardBtn.click();
        return this;
    }

    public ProjectPage typeDashboardName(String name) {
        $("input[placeholder='New dashboard']").val(name);
        return this;
    }

    @Step("Click on the 'Submit' button")
    public ProjectPage clickSubmit() {
        $(".Button_style_primary").click();
        return this;
    }

    @Step("Verify the dashboard with name:'{dashboardName}' has been created")
    public void verifyDashboardCreated(String dashboardName, String buttonName) {
        listOfDashboards.find(text(dashboardName)).shouldBe(visible);
        $(".Button_style_primary > span").shouldHave(text(buttonName));
    }

    @Step("Click on the 'More' button")
    public ProjectPage clickMoreButton() {
        $(".Menu__trigger").click();
        return this;
    }

    @Step("Perform action: '{action.name}'")
    public ProjectPage performAction(DashboardAction action) {
        // TODO: add switch case to make the method generic action
        listOfActions.find(text(action.getName())).click();
        $x("//span[contains(text(),'Delete')]").parent().click();
        return this;
    }

    @Step("Verify the dashboard with name:'{name}' has been removed")
    public void verifyDashboardRemoved(String dashboardName) {
        listOfDashboards.find(text(dashboardName)).shouldNotBe(visible);
    }

    @Step("Select dashboard with name: '{name}'")
    public ProjectPage selectDashboard(String name) {
        listOfDashboards.find(text(name)).parent().click();
        return this;
    }
}
