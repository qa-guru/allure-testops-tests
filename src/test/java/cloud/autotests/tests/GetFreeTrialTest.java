package cloud.autotests.tests;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GetFreeTrialTest extends TestBase {

    static Faker faker = new Faker(new Locale("en"));

    private SelenideElement
            fullNameInput = $(byName("text-110")),
            userEmailInput = $(byName("email-120")),
            companyNameInput = $(byName("text-111")),
            usersCountDropDown = $(byName("menu-130")),
            howUserHearAboutCompany = $(byName("menu-131")),
            acceptCheckbox = $(byName("acceptance-141"));

    private String
            fullName = faker.name().firstName(),
            email = faker.internet().emailAddress(),
            companyName = faker.company().name();

    private String
            getFreeTrialPageTitle = "Get started with your free 30-day Allure TestOps trial",
            successPageTitle = "Thank you for signing up to Allure TestOps",
            selectReferenceFromSomeOne = "Reference from someone I know",
            requestButton = "Request Trial";

    private String possibleUsersCount() {
        List<String> possibleUsersCountList = Arrays.asList("5", "10", "20", "50", "100+", "500+");
        return possibleUsersCountList.get(new Random().nextInt(possibleUsersCountList.size()));
    }

    @Test
    @Description("Get free trial test")
    @DisplayName("Check: the user could get a free trial")
    void getFreeTrialTest() {
        step("Open url https://qameta.io/free-trial/", () ->
                open("/free-trial/"));

        step("Check: the form for sending request is opened", () ->
                $(byText(getFreeTrialPageTitle)).waitUntil(appear, 6000).shouldBe(visible)
        );

        step("Fill all fields and send request", () -> {
            step("Fill all fields", () -> {
                fullNameInput.setValue(fullName);
                userEmailInput.setValue(email);
                companyNameInput.setValue(companyName);

                step("Select 'Expected users'", () -> {
                    usersCountDropDown.click();
                    $(byText(possibleUsersCount())).click();
                });

                step("Select 'How did you hear about us'", () -> {
                    howUserHearAboutCompany.click();
                    $(byText(selectReferenceFromSomeOne)).click();
                });

                acceptCheckbox.click();
            });

            step("Click the 'Request Trial' button", () -> {
                $(byValue(requestButton)).click();
            });
        });

        step("Check: request is sent", () ->
                $(byText(successPageTitle)).waitUntil(appear, 5000).shouldBe(visible)
        );
    }
}
