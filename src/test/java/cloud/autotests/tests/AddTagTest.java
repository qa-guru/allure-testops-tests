package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AddTagTest extends TestBase {

	private static final String PROJECT_NAME = "GOST_group_tests";
	String Tag = "new_tag" + (new Faker()).random().hex(3);

	@WithLogin
	@Test
	void AddNewTagAndDeleteAfterAssertion() {

		step("G0 to the project", () -> {
			$(".Input").val(PROJECT_NAME).pressEnter();
			$(byLinkText(PROJECT_NAME)).click();
		});

		step("Add new tag", () -> {
			$(".react-grid-item:nth-child(1) .Legend__item").click();
			$(".Checkbox__input").parent().click(); // выделить все тест-кейсы
			$(".LoadableTreeControlPanel > .Button").click(); //нажать на кнопку с выпадающим списком
			// действий
			$(".tippy-content").$(byText("Add tags")).click();
			$(".css-1hwfws3").click(); //клик на меню ввода текста
			$("#react-select-3-input").val(Tag);
			$("#react-select-3-option-0").click(); //подтвердить создание нового тэга
			$(byText("Submit")).click();
		});

		step("Assert that new tag exists", () -> {
			$(".TreeNodeName").click();
			$$(".TestCaseSectionTags__tags").find(text(Tag)).click();
			$(".TreeNodeName", 5).click();
			$$(".TestCaseSectionTags__tags").find(text(Tag)).click();
		});

		step("Remove added tag", () -> {
			$(".LoadableTreeControlPanel > .Button").click(); //нажать на кнопку с выпадающим списком
			// действий
			$(".tippy-content").$(byText("Remove tags")).click();
			$(".Menu__item:nth-child(11)").click();
			$(".css-1hwfws3").click(); //клик на меню ввода текста
			$("#react-select-4-input").val(Tag);
			$("#react-select-4-option-0").click(); //подтвердить создание нового тэга
			$(byText("Submit")).click();
		});

		step("Assert that 'new one' tag removed", () -> {
			$(".TreeNodeName").click();
			$(".PaneSection__content").shouldNotHave(text(Tag)).click();
			$(".TreeNodeName", 5).click();
			$(".PaneSection__content").shouldNotHave(text(Tag)).click();
		});
	}
}

