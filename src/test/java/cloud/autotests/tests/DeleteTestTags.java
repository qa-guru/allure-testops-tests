package cloud.autotests.tests;

import cloud.autotests.config.App;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Story("DeleteTestTags")
public class DeleteTestTags extends TestBase {

	@Test
	@DisplayName("Successful login as testuser")
	void loginTest() {
		step("Open login page", () -> {
			addTagTestRaw.OpenPage();
		});
		step("Fill login form", () -> {
			addTagTestRaw.FillLoginForm();
		});

		step("Fill Project name", () -> {
			addTagTestRaw.FillProjectname();
		});

		step("Go to test-cases", () -> {
			addTagTestRaw.GoToTestCases();
		});

		step("add new tag", () -> {
			addTagTestRaw.addNewTag();
		});

		step("assert new tag", () -> {
			addTagTestRaw.AssertTagAdd();
		});
	}
}