package cloud.autotests.tests.testCase;

import cloud.autotests.api.testCase.CreateTestCaseRequestDto;
import cloud.autotests.api.testCase.TestCaseApi;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cloud.autotests.data.TestCaseStatus.*;
import static cloud.autotests.data.TestCaseWorkflow.*;

@Story("Test case tests")
public class TestCaseTests extends BaseTest {

    // Test project [dont-remove-autotests-for-test-cases]
    private final static int PROJECT_ID = 1159;
    private final CreateTestCaseRequestDto testCase = CreateTestCaseRequestDto.builder()
            .name(faker.random().hex(15))
            .description(faker.random().hex(20))
            .projectId(PROJECT_ID)
            .build();

    @Test
    @WithLogin
    @DisplayName("Create test case")
    void createTestCase() {
        // Arrange
        testCasesListPage.openPage(PROJECT_ID);

        // Act
        testCasesListPage.unlockTestCasesTree();
        testCasesListPage.createNewTestCase(testCase.getName());
        testCasesListPage.loadTestCase(testCase.getName());
        int createdTestCaseId = testCasePage.getTestCaseId();

        // Assert
        testCasesListPage.checkThatTestCasesTableContainsTestCase(testCase.getName());
        testCasePage.checkThatTestCaseNameIs(testCase.getName());
        testCasePage.checkThatTestCaseDescriptionIs("No description");
        testCasePage.checkThatTestCaseStatusIs(DRAFT);

        // Cleaning data
        TestCaseApi.deleteTestCase(createdTestCaseId);
    }

    @Test
    @WithLogin
    @DisplayName("Edit test case")
    void editTestCase() {
        // Test data
        String newName = faker.random().hex(15);
        String newDescription = faker.random().hex(20);

        // Arrange
        int createdTestCaseId = TestCaseApi.createTestCase(testCase).getId();
        testCasePage.openPage(PROJECT_ID, createdTestCaseId);
        testCasesListPage.checkThatTestCasesTableContainsTestCase(testCase.getName());

        // Act
        testCasePage.editTestCaseName(newName);
        testCasePage.editTestCaseDescription(newDescription);
        testCasePage.changeTestCaseStatus(EXTENDED_MANUAL, ACTIVE);

        // Assert
        testCasePage.checkThatTestCaseNameIs(newName);
        testCasePage.checkThatTestCaseDescriptionIs(newDescription);
        testCasePage.checkThatTestCaseStatusIs(ACTIVE);

        // Cleaning data
        TestCaseApi.deleteTestCase(createdTestCaseId);
    }

    @Test
    @WithLogin
    @DisplayName("Delete test case")
    void deleteTestCase() {
        // Arrange
        int createdTestCaseId = TestCaseApi.createTestCase(testCase).getId();
        testCasePage.openPage(PROJECT_ID, createdTestCaseId);
        testCasesListPage.checkThatTestCasesTableContainsTestCase(testCase.getName());

        // Act
        testCasePage.deleteTestCase();

        // Assert
        testCasesListPage.checkThatTestCasesTableDoNotContainsTestCase(testCase.getName());
    }

}
