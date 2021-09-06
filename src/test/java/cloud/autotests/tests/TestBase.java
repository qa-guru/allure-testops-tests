package cloud.autotests.tests;

import cloud.autotests.api.AuthorizationApi;
import cloud.autotests.config.App;
import cloud.autotests.config.Project;
import cloud.autotests.helpers.AllureAttachments;
import cloud.autotests.helpers.DriverSettings;
import cloud.autotests.helpers.DriverUtils;
import cloud.autotests.helpers.ExtendedSelenideListener;
import cloud.autotests.api.model.Authorization;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    public static Authorization authorization = new Authorization();

    @BeforeAll
    static void setUp() throws JsonProcessingException {
        authorization = AuthorizationApi.getAuthorizationResponse();
        System.setProperty("AUTH_2", authorization.asString());
        System.setProperty("accessToken", String.format("Bearer %s", authorization.getAccess_token()));

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
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}