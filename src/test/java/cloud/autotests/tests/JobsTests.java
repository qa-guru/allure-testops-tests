package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.JobsPage;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Jobs tests")
public class JobsTests extends TestBase {

    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";
    private static final String BUILD_SERVER_NAME = "jenkins.autotests.cloud";
    private static final String JOB_NAME = "05-test";

    public String getJOB_NAME() {
        return JOB_NAME;
    }

    @WithLogin
    @Test
    void userCanCreateNewJob() {
        JobsPage jobsPage = new JobsPage();

        step("Переходим на страницу Job", () -> {
            ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
            ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
            projectPage.getSidebar().navigateTo(MenuItem.JOBS);
        });

        step("Создаем новую задачу", () -> {
            jobsPage.clickButtonNewJob();
            jobsPage.inputBuildServer(BUILD_SERVER_NAME);
            jobsPage.clickCanRunCheckbox();
            jobsPage.inputJob(JOB_NAME);
            jobsPage.addParameter("testname", "111", "Branch");
            jobsPage.clickButtonSubmit();
        });

        step("Проверяем, что задача успешно создана", () -> {
            jobsPage.checkJob();
        });

        step("Удаляем созданную задачу, проверяем успешное удаление", () -> {
            jobsPage.deleteJob();
        });
    }

}
