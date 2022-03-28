package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Pagination tests")
public class PaginationTests extends TestBase {

    @CsvSource(value = {
            "0,5",
            "1,10",
            "2,25",
            "3,50",
            "4,100"
    })
    @WithLogin
    @ParameterizedTest(name = "Сhanging the pagination display (for example, {1} elements)")
    void paginationOfSomeElements(int elementIdSuffix, int expectedCountInText) {
        step("Open projects page", () ->
                open(""));
        step("Open pagination select", () ->
                $(".css-19attta-control").click());
        step("Choice " + expectedCountInText + " elements", () ->
                $(byId("react-select-2-option-" + elementIdSuffix)).click());
        step("Check text 'Show " + expectedCountInText + "'", () ->
                $(".css-1hwfws3").should(text("Show " + expectedCountInText)));
    }
}
