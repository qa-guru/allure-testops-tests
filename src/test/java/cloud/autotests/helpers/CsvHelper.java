package cloud.autotests.helpers;

import com.opencsv.CSVReader;
import io.qameta.allure.Step;

import java.io.*;
import java.util.List;

public class CsvHelper {

    @Step("Read all strings from json file")
    public static List<String[]> readAll(File file) {
        try (FileInputStream inputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            CSVReader reader = new CSVReader(inputStreamReader);
            return reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during parse file " + file.getAbsoluteFile());
        }
    }

}
