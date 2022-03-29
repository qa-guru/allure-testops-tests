package cloud.autotests.tests;

import cloud.autotests.api.testCaseTag.TestCaseTagApi;
import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Test case tags tests")
public class TestCaseTagsTests extends TestBase {

	private static final int PROJECT_ID = 291;		// Project name [GOST_group_tests]
	private static final int TEST_CASE_ID = 7962;	// Test case name [Test case for autotest [Create new tag on test case]]

	@Test
	@WithLogin
	@DisplayName("Create new tag on test case")
	void createNewTag() {
		// Test data
		String oldTagName = "OLD TAG NAME";
		String newTagName = faker.random().hex(10);

		// Arrange
		testCasePage.openPage(PROJECT_ID, TEST_CASE_ID);

		// Act
		testCasePage.addNewTag(newTagName);

		// Assert
		testCasePage.checkThatTagsSectionContainsTag(oldTagName, newTagName);

		// Cleaning data
		// Удаляем новый созданный tag
		Integer newTagId = TestCaseTagApi.getTestCaseTagId(TEST_CASE_ID, newTagName);
		TestCaseTagApi.deleteTestCaseTagId(newTagId);
	}

}
