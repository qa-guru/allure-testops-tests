package cloud.autotests.tests.testcase;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.tests.TestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("crud")
public class CommentTests extends TestBase {
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";


    @WithLogin
    @Test
    @Disabled
    void addCommentTest() {
        // create project by api
        // create testcase by api
        // open testcase in ui
        // add comment in ui
        // check  comment
        // delete comment, testcase, project by api
        ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);


        step("Open active test case", () -> {

        });

        step("Add and delete comment", () -> {
            $(".MarkdownTextarea__edit").setValue("test comment");
            $(".Button_style_success ").click();

            $(".Comment__header").$("svg").click();
            $(".tippy-content").find(byText("Delete")).click();
        });
    }

    void editCommentTest() {
        // create project by api
        // create testcase by api
        // add comment by api
        // open testcase in ui
        // edit comment in ui
        // check comment
        // delete comment, testcase, project by api
    }

    void deleteCommentTest() {
        // create project by api
        // create testcase by api
        // add comment by api
        // open testcase in ui
        // delete comment in ui
        // check comment
        // delete comment, testcase, project by api
    }
}
