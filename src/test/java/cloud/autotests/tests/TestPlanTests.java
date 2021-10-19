package cloud.autotests.tests;

import cloud.autotests.pages.TestPlansPage;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class TestPlanTests {
    TestPlansPage testPlanPage = new TestPlansPage();
    String testPlanName = "auto_regress_01_" + (new Faker()).random().hex(2);

    // @WithLogin
    @Test
    public void createTestPlan() {
       /* ProjectResponse projectResponse = new Project().createProject2("Project for test plan creation " + System.currentTimeMillis(), true);
        Integer idProject = projectResponse.getId();*/
        Integer idProject = 591;//
        open(String.format("https://allure.autotests.cloud/project/%s/testplans", idProject));

        //убрать логин через ui, когда будет работать авторизация через api
        $x("//*[@name='username']").setValue("qaguru6");
        $x("//*[@name='password']").setValue("qaguru");
        $x("//*[@type='submit']").click();

        testPlanPage.clickButtonNewTestPlan();
        testPlanPage.enterNameNewTestPlan(testPlanName);
        testPlanPage.pressButtonNext();
        testPlanPage.pressButtonCreateTestPlan();

        String message = testPlanPage.checkMessage();
        Assertions.assertThat(message)
                .as("")
                .contains(String.format("Created %s", testPlanName));

        testPlanPage.goToPageTestPlans();
        testPlanPage.findTestPlan(testPlanName);

        String idTestPlane = testPlanPage.getIdTestPlan();

        String link = testPlanPage.checkLinkTestPlan(testPlanName);
        assertThat(link).isEqualTo("https://allure.autotests.cloud/testplan/" + idTestPlane + "");
    }
}
