package cloud.autotests.tests.testCase;

import cloud.autotests.data.MenuItem;
import cloud.autotests.data.PropertyName;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.testCase.TestCaseProperties;
import cloud.autotests.pages.testCase.TestCasesListPage;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Properties of test cases")
public class TestCasePropertiesTest extends BaseTest {

    // Test project [teacher qa_guru_diplom_project]
    private static final int PROJECT_ID = 43;
    private static final String STATUS_NAME = "New";

    @Test
    @WithLogin
    @DisplayName("Check test case properties")
    void checkTestProperties(){
        projectPage.openPage(PROJECT_ID);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        TestCasesListPage testCasesTable = new TestCasesListPage();
        testCasesTable.navigateToTestByStatus(STATUS_NAME);

        TestCaseProperties testProperties = new TestCaseProperties();
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
