package cloud.autotests.helpers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Step;
import lombok.Getter;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvHelper {

    @Getter
    private final File file;
    private final CSVReader reader;

    public CsvHelper(File file) throws FileNotFoundException {
        if (!file.exists())
            throw new RuntimeException("File " + file.getAbsoluteFile() + " don't exist");
        this.file = file;

        FileInputStream inputStream = new FileInputStream(file);
        reader = new CSVReader(new InputStreamReader(inputStream));
    }

    @Step("Convert csv to list")
    public List<String[]> convertToList() throws IOException, CsvException {
        return reader.readAll();
    }

    @Step("Csv file should have size [{expectedSize}]")
    public void shouldHaveSize(int expectedSize) throws IOException, CsvException {
        assertEquals(expectedSize, convertToList().size());
    }

}
