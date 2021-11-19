package cloud.autotests.tests;
import cloud.autotests.helpers.WithLogin;
import com.github.javafaker.Faker;
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
	void addNewTagAndDeleteAfterAssertion() {
		step("G0 to the project", () -> {
			$(".Input").val(PROJECT_NAME).pressEnter();
			$(byLinkText(PROJECT_NAME)).click();
		});

		step("Add new tag", () -> {
			$(".Legend__item_link").click();
			$(".Checkbox__input").parent().click(); // выделить все тест-кейсы
			$(".LoadableTreeControlPanel > .Button").click(); //нажать на кнопку с выпадающим списком
			// действий
			$(".tippy-content").$(byText("Add tags")).click();
			$(byText("Select tag")).click(); //клик на меню ввода текста
			$("#react-select-3-input").val(Tag);
			$("#react-select-3-option-0").click(); //подтвердить создание нового тэга
			$(byText("Submit")).click();
		});

		step("Assert that new tag exists", () -> {
			$(".TreeNodeName").click();
			$(".TestCaseSectionTags__tags").shouldHave(text(Tag)).click();
		});

		step("Remove added tag", () -> {
			$(".LoadableTreeControlPanel > .Button").click(); //нажать на кнопку с выпадающим списком
			// действий
			$(".tippy-content").$(byText("Remove tags")).click();
			$(byText("Select tag")).click(); //клик на меню ввода текста
			$("#react-select-4-input").val(Tag);
			$("#react-select-4-option-0").click(); //подтвердить создание нового тэга
			$(byText("Submit")).click();
		});

		step("Assert that 'new one' tag removed", () -> {
			$(".TreeNodeName").click();
			$(".PaneSection__content").shouldNotHave(text(Tag)).click();
		});
	}
}

