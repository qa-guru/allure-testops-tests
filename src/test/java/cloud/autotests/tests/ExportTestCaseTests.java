package cloud.autotests.tests;

import cloud.autotests.helpers.CsvHelper;
import cloud.autotests.helpers.WithLogin;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ExportTestCaseTests extends BaseTest {

    private final static int PROJECT_ID = 1159;

    @Test
    @WithLogin
    @DisplayName("Export test case to csv")
    void exportTestCaseToCsv() throws IOException, CsvException {
        // Test data
        int[] existTestCases = new int[] { 8751, 8752, 8753 };

        // Arrange
        testCasesListPage.openPage(PROJECT_ID);

        // Act
        testCasesListPage.selectTestCases(existTestCases);
        File file = testCasesListPage.exportToCsv();
        CsvHelper csvHelper = new CsvHelper(file);

        // Assert
        // В ожидаемом результате + 1, тк есть заголовок таблицы
        csvHelper.shouldHaveSize(existTestCases.length + 1);

        // ToDo Добавить более детальную проверку содержимого csv-файла
    }

}
