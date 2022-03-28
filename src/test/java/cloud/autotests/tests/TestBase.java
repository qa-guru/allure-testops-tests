package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.config.Project;
import cloud.autotests.helpers.AllureAttachments;
import cloud.autotests.helpers.DriverSettings;
import cloud.autotests.helpers.DriverUtils;
import cloud.autotests.helpers.ExtendedSelenideListener;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsListPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {

    protected ProjectsListPage projectsListPage = new ProjectsListPage();
    protected ProjectPage projectPage = new ProjectPage();


    @BeforeAll
    static void setUp() {
        DriverSettings.configure();
        RestAssured.baseURI = App.config.apiUrl();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new ExtendedSelenideListener());
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
//        AllureAttachments.attachNetwork(); // todo
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
