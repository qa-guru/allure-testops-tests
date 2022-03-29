package cloud.autotests.pages;

import cloud.autotests.data.MenuItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsTable {

    private ElementsCollection tableRows = $$("li.list-row");

    @Step("Navigate to project `{projectName}`")
    public ProjectPage navigateTo(String projectName) {
        tableRows.find(text(projectName)).$(".ProjectCard__name > a").click();
        return new ProjectPage();
    }

    public int getProjectTableSize() {
        return tableRows.size();
    }
}
