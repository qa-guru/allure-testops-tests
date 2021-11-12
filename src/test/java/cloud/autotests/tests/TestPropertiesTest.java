package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.data.PropertyName;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import cloud.autotests.pages.TestProperties;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Story("Properties of Tests")
public class TestPropertiesTest extends TestBase{
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";
    private static final String STATUS_NAME = "New";

    @WithLogin
    @Test
    void checkTestProperties(){
        ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        TestCasesTable testCasesTable = new TestCasesTable();
        testCasesTable.navigateToTestByStatus(STATUS_NAME);

        TestProperties testProperties = new TestProperties();
        testProperties.shouldHaveSize(8);

        testProperties.shouldHaveElement(PropertyName.TAGS.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.HISTORY.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.ISSUE_HISTORY.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.MEMBERS.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.FIELDS.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.MUTES.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.RELATIONS.getDisplayedName());
        testProperties.shouldHaveElement(PropertyName.DESCRIPTIONS.getDisplayedName());

    }
}
