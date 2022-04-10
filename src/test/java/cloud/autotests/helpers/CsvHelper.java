package cloud.autotests.helpers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Step;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    private final CSVReader reader;

    public CsvHelper(CSVReader reader) {
        this.reader = reader;
    }

    public static CsvHelper of(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader reader = new CSVReader(inputStreamReader);
            return new CsvHelper(reader);
        } catch (IOException exception) {
            throw new RuntimeException("File " + file.getAbsoluteFile() + " don't exist");
        }
    }

    @Step("Convert csv to list")
    public List<String[]> convertToList() {
        try {
            return reader.readAll();
        } catch (IOException | CsvException exception) {
            return new ArrayList<>();
        }
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
