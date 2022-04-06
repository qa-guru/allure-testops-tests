package cloud.autotests.tests.project;

import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Story("Pagination tests")
public class PaginationTests extends BaseTest {

    @CsvSource(value = {
            "0,5",
            "1,10",
            "2,25",
            "3,50",
            "4,100"
    })
    @WithLogin
    @ParameterizedTest(name = "Change the pagination display (for example, {1} elements)")
    void paginationOfSomeElements(int elementIdSuffix, int expectedCountInText) {
        projectsListPage.openPage();
        step("Open pagination select", () ->
                $(".Pagination__select").click());
        step("Choice " + expectedCountInText + " elements", () ->
                $(byId("react-select-2-option-" + elementIdSuffix)).click());
        step("Check text about pagination info", () -> {
            $(".Pagination__size").shouldHave(text("Items per page:"));
            $(".Pagination__select").shouldHave(text(Integer.toString(expectedCountInText)));
            $(".Pagination__indices").shouldHave(text("1 – " + expectedCountInText));
        });
    }

}
