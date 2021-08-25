package cloud.autotests.pages;

import cloud.autotests.tests.JobsTests;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class JobsPage {

    private static final SelenideElement newJobButton = $(byText("New job"));
    private static final SelenideElement submitButton = $(byText("Submit"));
    private static final SelenideElement buildServer = $(byXpath("(//input[@type='text'])[1]"));
    private static final SelenideElement canRunCheckbox = $(byText("Can run tests"));
    private static final SelenideElement job = $(byXpath("(//input[@type='text'])[2]"));
    private static final SelenideElement buttonAddParameter = $(byText("Add parameter"));
    private static final SelenideElement parameterName = $(byName("name"));
    private static final SelenideElement parameterValue = $(byName("defaultValue"));
    private static final SelenideElement environmentVariable = $(byText("Environment variable"));

    JobsTests jobsTests = new JobsTests();

    public void clickButtonNewJob() {
        newJobButton.click();
        $(".Modal__name").shouldHave(text("Create job"));
    }

    public void inputBuildServer(String buildServerName) {
        buildServer.setValue(buildServerName);
        sleep(1000);
        buildServer.pressTab();
    }

    public void clickCanRunCheckbox() {
        canRunCheckbox.click();
    }

    public void inputJob(String jobName) {
        job.setValue(jobName);
        sleep(1000);
        job.pressTab();
    }

    public void addParameter(String name, String value, String variable) {
        buttonAddParameter.click();
        parameterName.setValue(name);
        parameterValue.setValue(value);
        environmentVariable.click();
        $(byText(variable)).click();
    }

    public void clickButtonSubmit() {
        submitButton.click();
    }

    public void checkJob() {
        String jobName = jobsTests.getJOB_NAME();
        $(".Job__name").shouldHave(text(jobName));
        $(byXpath("//a[@href='https://jenkins.autotests.cloud/job/" + jobName + "/']")).shouldBe(visible);
    }

    public void deleteJob() {
        $(byXpath("(//button)[4]")).click();
        $(byText("Delete")).click();
        $(".Modal__header").shouldHave(text("Delete " + jobsTests.getJOB_NAME() + "?"));
        $(".Modal__content").shouldHave(text("Deleted jobs can't be restored"));
        $(byText("Delete")).click();
        $(".Job__name").shouldNotHave(text(jobsTests.getJOB_NAME()));
    }
}
