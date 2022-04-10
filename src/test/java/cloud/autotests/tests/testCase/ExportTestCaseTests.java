package cloud.autotests.tests.testCase;

import cloud.autotests.helpers.CsvHelper;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

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
        CsvHelper csvReader = CsvHelper.of(file);
        List<String[]> csvToList = csvReader.convertToList();
        csvReader.close();

        // Assert
        // В ожидаемом результате + 1, тк есть заголовок таблицы
        step("Csv file should have size [" + existTestCases.length + "]", () ->
                assertEquals(existTestCases.length + 1, csvToList.size()));

        // ToDo Добавить более детальную проверку содержимого csv-файла
    }

}
