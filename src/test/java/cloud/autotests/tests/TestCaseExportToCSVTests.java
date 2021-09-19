package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Story("Export test case to CSV tests")
public class TestCaseExportToCSVTests extends TestBase {
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";
    private String testCaseName = "2) Проверка работы слайдера на главной странице";

    @Test
    @WithLogin
    void exportToCSV() {
        ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        TestCasesTable testCasesTable = new TestCasesTable();
        testCasesTable.chooseTestCase(testCaseName);
        testCasesTable.clickBulkActionsButton();
        testCasesTable.exportTestCaseToCSV();
        testCasesTable.checkExportToCSVFinished();
    }
}
