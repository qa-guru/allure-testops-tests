package cloud.autotests.tests;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class FindProject extends TestBase{

    @Test
    void searchTest(){
        step("Enter project name", () -> {
            open("https://allure.autotests.cloud/");
            $("input.HomeLayout__search").setValue("allure-testops-tests").pressEnter();
        });

        step("Check results", () -> {
           $(".ProjectCard__name").shouldHave(Condition.text("allure-testops-tests"));
        });

    }
}
