package cloud.autotests.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ExportTestCaseToCsvDialog {

    @Step("Export test case to CSV")
    public void exportTestCaseToCSV() {
        $(".Form__controls").$(byText("Submit")).click();
    }

    @Step("Check export to CSV finished")
    public void checkExportToCSVFinished() {
        $(".Label_status_passed").shouldHave(text("ready"));
        $(".ExportRequest__link").$("a").shouldBe(Condition.enabled);
    }

    @Step("Download CSV file")
    public File downloadCsvFile() throws FileNotFoundException {
       return $(".ExportRequest__link").download(DownloadOptions.using(FileDownloadMode.FOLDER));
    }

}
