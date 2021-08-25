package cloud.autotests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsTable {

    private ElementsCollection tableRows = $$("li.list-row");
    private SelenideElement searchField = $(".HomeLayout__search");
    private SelenideElement listOfProjects = $(".list");

    @Step("Navigate to project `{projectName}`")
    public ProjectPage navigateTo(String projectName) {
        tableRows.find(text(projectName)).$(".ProjectCard__name > a").click();
        return new ProjectPage();
    }

    public ProjectsTable enterValueInSearchField(String searchValue) {
        searchField.val(searchValue);
        return this;
    }

    public ProjectsTable checkThatListOfProjectsContainsProject(String projectName) {
        listOfProjects.shouldHave(text(projectName));
        return this;
    }
}
