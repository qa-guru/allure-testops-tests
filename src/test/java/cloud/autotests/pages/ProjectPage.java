package cloud.autotests.pages;

import cloud.autotests.pages.components.Sidebar;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {

    private Sidebar sidebar = new Sidebar();

    private ElementsCollection widgets = $$(".WidgetOld");

    private SelenideElement testCasesWidget = widgets.find(text("Test cases"));
    private SelenideElement automationWidget = widgets.find(text("Automation"));
    private SelenideElement launchesWidget = widgets.find(text("Launches"));
    private SelenideElement automationTrendWidget = widgets.find(text("Automation trend"));
    private SelenideElement muteTrendWidget = widgets.find(text("Mute trend"));


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
}
