package cloud.autotests.tests;

import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsListPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import cloud.autotests.pages.components.ExportTestCaseToCsvDialog;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.qameta.allure.Allure.step;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

@Story("Export test case to CSV tests")
public class TestCaseExportToCSVTests extends TestBase {
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";
    public static final String EXPECTED_CSV_PATH = "src/test/resources/export/report.csv";
    private static final String TEST_CASE_NAME = "2) Проверка работы слайдера на главной странице";

    @Test
    @WithLogin
    void exportToCSV() {
        ProjectsListPage projectsListPage = new ProjectsListPage().openPage();
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectsTable projectsTable = projectsListPage.getProjectsTable();
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        TestCasesTable testCasesTable = new TestCasesTable();
        testCasesTable.chooseTestCase(TEST_CASE_NAME);

        ExportTestCaseToCsvDialog exportTestCaseToCsvDialog = testCasesTable.openExportTestCaseToCsvDialog();
        exportTestCaseToCsvDialog.exportTestCaseToCSV();
        exportTestCaseToCsvDialog.checkExportToCSVFinished();

        step("File content equals to expected one", () ->
                assertThat(exportTestCaseToCsvDialog.downloadCsvFile())
                        .hasSameTextualContentAs(new File(EXPECTED_CSV_PATH), UTF_8));
    }
}
