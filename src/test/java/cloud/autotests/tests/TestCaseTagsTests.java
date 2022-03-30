package cloud.autotests.tests;

import cloud.autotests.api.testCaseTag.*;
import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Test case tags tests")
public class TestCaseTagsTests extends TestBase {

	// Project name [GOST_group_tests]
	private static final int PROJECT_ID = 291;
	private final String randomTagName = faker.random().hex(10);

	@Test
	@WithLogin
	@DisplayName("Create new tag from test case detail")
	void createNewTag() {
		// Test data
		// Test case name [Test case for autotest [Create new tag on test case]]
		int testCaseId = 7962;
		String oldTagName = "OLD TAG NAME";

		// Arrange
		testCasePage.openPage(PROJECT_ID, testCaseId);

		// Act
		testCasePage.addNewTag(randomTagName);

		// Assert
		testCasePage.checkThatTagsSectionContainsTag(oldTagName, randomTagName);

		// Cleaning data
		// Удаляем новый созданный tag
		Integer newTagId = TestCaseTagApi.getTestCaseTagId(testCaseId, randomTagName);
		TestCaseTagApi.deleteTestCaseTagId(newTagId);
	}

	@Test
	@WithLogin
	@DisplayName("Remove test case tag via bulk action")
	void removeTagViaBulkAction() {
		// Test data
		// Test case name [Test case for autotest [Remove test case tag via bulk action]]
		int testCaseId = 7980;

		// Arrange
		TestCaseTagDto tag = TestCaseTagApi.createNewTestCaseTag(randomTagName);
		TestCaseTagApi.setTestCaseTags(testCaseId, tag);
		testCasePage.openPage(PROJECT_ID, testCaseId);
		testCasePage.checkThatTagsSectionContainsTag(tag.getTagName());

		// Act
		testCasePage.testCasesTable.selectTestCase(testCaseId);
		testCasePage.testCasesTable.removeTagViaBulkAction(tag.getTagName());

		// Assert
		testCasePage.checkThatTagsSectionDoNotContainsTag(tag.getTagName());
	}

}
