package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsTable {

    private ElementsCollection tableRows = $$("li.list-row");

    @Step("Navigate to project `{projectName}`")
    public ProjectPage navigateTo(String projectName) {
        tableRows.find(text(projectName)).$(".ProjectCard__name > a").click();
        return new ProjectPage();
    }

    @Step("Click first Launches button")
    public LaunchesPage clickFirstLaunchesBtn() {
        $(".ProjectCard").$(".ProjectCard__links").$(".ProjectCard__link", 2).click();
        return new LaunchesPage();
    }
}
