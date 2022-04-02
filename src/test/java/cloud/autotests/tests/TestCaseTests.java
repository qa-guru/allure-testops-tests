package cloud.autotests.tests;

import cloud.autotests.api.testCase.CreateTestCaseRequestDto;
import cloud.autotests.api.testCase.TestCaseApi;
import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cloud.autotests.data.TestCaseStatus.*;
import static cloud.autotests.data.TestCaseWorkflow.*;

@Story("Test case tests")
public class TestCaseTests extends TestBase {

    // Test project [dont-remove-autotests-for-test-cases]
    private final static int PROJECT_ID = 1159;
    private final CreateTestCaseRequestDto testCase = CreateTestCaseRequestDto.builder()
            .name(faker.random().hex(15))
            .description(faker.random().hex(20))
            .projectId(PROJECT_ID)
            .build();
    private Integer createdTestCaseId;

    @AfterEach
    void deleteCreatedTestCase() {
        // Cleaning data
        // createdTestCaseId != null, значит в рамках теста создан новый тест-кейс, который необходимо подчистить
        if (createdTestCaseId != null)
            TestCaseApi.deleteTestCase(createdTestCaseId);
    }

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
        createdTestCaseId = testCasePage.getTestCaseId();

        // Assert
        testCasesListPage.checkThatTestCasesTableContainsTestCase(testCase.getName());
        testCasePage.checkThatTestCaseNameIs(testCase.getName());
        testCasePage.checkThatTestCaseDescriptionIs("No description");
        testCasePage.checkThatTestCaseStatusIs(DRAFT);
    }

    @Test
    @WithLogin
    @DisplayName("Edit test case")
    void editTestCase() {
        // Test data
        String newName = faker.random().hex(15);
        String newDescription = faker.random().hex(20);

        // Arrange
        createdTestCaseId = TestCaseApi.createTestCase(testCase).getId();
        testCasePage.openPage(PROJECT_ID, createdTestCaseId);
        testCasesListPage.checkThatTestCasesTableContainsTestCase(testCase.getName());

        // Act
        testCasePage.editTestCaseName(newName);
        testCasePage.editTestCaseDescription(newDescription);
        testCasePage.changeTestCaseStatus(DEFAULT_MANUAL, ACTIVE);

        // Assert
        testCasePage.checkThatTestCaseNameIs(newName);
        testCasePage.checkThatTestCaseDescriptionIs(newDescription);
        testCasePage.checkThatTestCaseStatusIs(ACTIVE);
    }

    @Test
    @WithLogin
    @DisplayName("Delete test case")
    void deleteTestCase() {
        // Arrange
        createdTestCaseId = TestCaseApi.createTestCase(testCase).getId();
        testCasePage.openPage(PROJECT_ID, createdTestCaseId);
        testCasesListPage.checkThatTestCasesTableContainsTestCase(testCase.getName());

        // Act
        testCasePage.deleteTestCase();

        // Assert
        testCasesListPage.checkThatTestCasesTableDoNotContainsTestCase(testCase.getName());

        // Присваиваю createdTestCaseId = null,
        // тк удалять тест-кейс через API (в @AfterEach) не нужно (потому что мы его удалили через UI)
        createdTestCaseId = null;
    }

}