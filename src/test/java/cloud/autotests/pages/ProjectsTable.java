package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProjectsTable {

    private ElementsCollection tableRows = $$("li.list-row");
    private SelenideElement searchField = $(".HomeLayout__search");
    private SelenideElement listOfProjects = $(".list");

    @Step("Open Projects list page")
    public ProjectsTable openProjectsTablePage() {
        open("");
        return this;
    }

    @Step("Navigate to project `{projectName}`")
    public ProjectPage navigateTo(String projectName) {
        tableRows.find(text(projectName)).$(".ProjectCard__name > a").click();
        return new ProjectPage();
    }

    public int getProjectTableSize() {
        return tableRows.size();
    }

    @Step("Enter project name in Search field")
    public ProjectsTable searchProject(String searchValue) {
        searchField.val(searchValue);
        return this;
    }

    @Step("Check that project is displayed in search results")
    public ProjectsTable checkThatListOfProjectsContainsProject(String projectName) {
        listOfProjects.shouldHave(text(projectName));
        return this;
    }
}
