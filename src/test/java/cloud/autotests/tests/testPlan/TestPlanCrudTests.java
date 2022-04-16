package cloud.autotests.tests.testPlan;

import cloud.autotests.api.testPlan.CreateTestPlanRequestDto;
import cloud.autotests.api.testPlan.TestPlanApi;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.testPlan.TestPlanPage;
import cloud.autotests.pages.testPlan.TestPlansListPage;
import cloud.autotests.tests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPlanCrudTests extends BaseTest {

    private final TestPlansListPage testPlansListPage = new TestPlansListPage();
    private final TestPlanPage testPlanPage = new TestPlanPage();

    private final TestPlanApi testPlanApi = new TestPlanApi();

    // Test project [dont-remove-autotests-test-plans]
    private final static int PROJECT_ID = 1178;
    private final CreateTestPlanRequestDto testPlan = CreateTestPlanRequestDto.builder()
            .name(faker.random().hex(20))
            .projectId(PROJECT_ID)
            .build();

    @Test
    @WithLogin
    @DisplayName("Create test plan")
    void createTestPlan() {
        // Test data
        int projectTestCasesCount = 3;

        // Arrange
        testPlansListPage.openPage(PROJECT_ID);

        // Act
        testPlansListPage.createNewTestPlan(testPlan.getName());

        // Assert
        testPlanPage.checkThatSystemShowCreatedTestPlanPopup(testPlan.getName());
        testPlanPage.checkThatTestPlanNameIs(testPlan.getName());
        testPlanPage.checkThatTestPlanHasSize(projectTestCasesCount);

        // Act
        int createdTestPlan = testPlanPage.getTestPlanId();
        testPlansListPage.openPage(PROJECT_ID);

        // Assert
        testPlansListPage.checkThatTestPlansListContainsTestPlan(testPlan.getName());

        // Cleaning data
        testPlanApi.deleteTestPlan(createdTestPlan);
    }

    @Test
    @WithLogin
    @DisplayName("Delete test plan")
    void deleteTestPlan() {
        // Arrange
        int createdTestPlan = testPlanApi.createTestPlan(testPlan).getId();
        testPlanPage.openPage(createdTestPlan);

        // Act
        testPlanPage.deleteTestPlan();

        // Assert
        testPlansListPage.checkThatTestPlansListDoNotContainsTestPlan(testPlan.getName());
    }

}
