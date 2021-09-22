package cloud.autotests.tests.testcase;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.DriverUtils;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import cloud.autotests.pages.components.Sidebar;
import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class TestCasesInfoTests extends TestBase {
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";


    @WithLogin
    @Test
    @Disabled("Cut to different test classes")
    void TestCasesPageShouldContainValidInfo() {
        ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);


        step("Open active test case", () -> {
            $$(".LoadableTree__view > li").findBy(text("Active")).click();
            $(".TestCaseLayout__name").shouldBe(visible);
        });

        step("Open History", () -> {
            $(".Pane").find(byText("History")).click();
            $(".TestResultHistoryList__header").shouldBe(visible);
        });

        step("Open Attachments", () -> {
            $(".Pane").find(byText("Attachments")).click();
            $(".TestCaseAttachments__count").shouldBe(visible);
        });

        step("Open Mutes", () -> {
            $(".Pane").find(byText("Mutes")).click();
            $(".TestCaseMutes__count").shouldBe(visible);
        });

        step("Open Defects", () -> {
            $(".Pane").find(byText("Defects")).click();
            $(".TestCaseDefects__count").shouldBe(visible);
        });
    }
}
