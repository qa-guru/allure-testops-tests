package cloud.autotests.pages.project;

import cloud.autotests.pages.components.Sidebar;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {

    private final Sidebar sidebar = new Sidebar();

    @Step("Open project by id [{projectId}]")
    public void openPage(int projectId) {
        open("/project/" + projectId);
    }

    @Step("Get Sidebar")
    public Sidebar getSidebar() {
        return sidebar;
    }

    @Step("Delete project")
    public void deleteProject() {
        $$("button.Button_style_danger").find(text("Delete project")).click();
        $$("button.Button_style_danger").find(exactText("Delete")).click();
    }

    @Step("Verify title has text [{title}]")
    public void checkTitle(String title) {
        $(".ProjectDashboards__title a").shouldHave(text(title));
    }

    @Step("Verify project's all widgets are displayed")
    public void checkThatProjectHas5Widgets() {
        $$(".WidgetOld__title").shouldHave(
                itemWithText("Test cases"),
                itemWithText("Automation"),
                itemWithText("Launches"),
                itemWithText("Automation Trend"),
                itemWithText("Mute Trend")
        );
    }

}
