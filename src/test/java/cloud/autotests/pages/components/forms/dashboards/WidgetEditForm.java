package cloud.autotests.pages.components.forms.dashboards;

import cloud.autotests.data.dashboards.FormGroupByItem;
import cloud.autotests.data.dashboards.FormLaunchAnalyticMetricItem;
import cloud.autotests.data.dashboards.FormTopTestCasesMetricItem;
import cloud.autotests.data.dashboards.FormTreeItem;
import cloud.autotests.data.dashboards.FormTypeItem;
import cloud.autotests.pages.components.forms.FormBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WidgetEditForm extends FormBase {

    private ElementsCollection contentFields = getForm().$$(".FormLabel__name");
    private ElementsCollection dropDawnList = $(".css-11unzgr").$$("div");

    private SelenideElement nameInput = getFieldByLabel("Name").$(".Input");
    private SelenideElement nameErrorMessage = getFieldByLabel("Name").$(".Form__error");
    private SelenideElement contentWriteTextArea = getFieldByLabel("Content").$(".MarkdownTextarea__edit");
    private SelenideElement testCasesQueryInput = getFieldByLabel("Test cases query").$(".Input");
    private SelenideElement testCasesQueryStatus = getFieldByLabel("Test cases query")
            .$(".InputQuery__status");
    private SelenideElement testCasesQueryCount = getFieldByLabel("Test cases query")
            .$(".InputQuery__count");
    private SelenideElement launchersQueryInput = getFieldByLabel("Launchers query").$(".Input");
    private SelenideElement launchersQueryStatus = getFieldByLabel("Launchers query")
            .$(".InputQuery__status");
    private SelenideElement launchersQueryCount = getFieldByLabel("Launchers query")
            .$(".InputQuery__count");

    private SelenideElement typeSelect = getFieldByLabel("Type").$(".Select");
    private SelenideElement metricSelect = getFieldByLabel("Metric").$(".Select");
    private SelenideElement groupBySelect = getFieldByLabel("Group By").$(".Select");
    private SelenideElement treeSelect = getFieldByLabel("Tree").$(".Select");

    private SelenideElement formControls = getForm().$(".Form__controls");
    private SelenideElement cancelButton = formControls.find(byText("Cancel"));
    private SelenideElement submitButton = formControls.find(byText("Submit"));

    //region Get methods
    private SelenideElement getFieldByLabel(String fieldLabel) {
        return contentFields.find(text(fieldLabel)).parent();
    }

    private SelenideElement getSelectedItem(String listItemName) {
        return dropDawnList.find(text(listItemName));
    }
    //endregion

    //region Entering data steps
    @Step("Set value '{value}' in input field 'Name'")
    public WidgetEditForm setNameInput(String value) {
        nameInput.val(value);
        return this;
    }

    @Step("Fill write textarea of form content field")
    public WidgetEditForm fillContentWriteTextArea(String textarea) {
        contentWriteTextArea.val(textarea);
        return this;
    }

    @Step("Set value '{value}' in input field 'Test cases query'")
    public WidgetEditForm setTestCasesQueryInput(String value) {
        testCasesQueryInput.val(value);
        return this;
    }

    @Step("Set value '{value}' in input field 'Launchers query'")
    public WidgetEditForm setLaunchersQueryValue(String value) {
        launchersQueryInput.val(value);
        return this;
    }
    //endregion

    //region Check data steps
    @Step("Check that 'Name' error message is '{errorMessage}'")
    public WidgetEditForm checkThatNameErrorMessageIs(String errorMessage) {
        nameErrorMessage.shouldHave(text(errorMessage));
        return this;
    }

    @Step("Check that 'Test Cases Query' status is '{status}'")
    public WidgetEditForm checkThatTestCasesQueryStatusIs(String status) {
        testCasesQueryStatus.shouldHave(text(status));
        return this;
    }

    @Step("Check that 'Test Cases Query' count is '{count}'")
    public WidgetEditForm checkThatTestCasesQueryCountIs(String count) {
        testCasesQueryCount.shouldHave(text(count));
        return this;
    }

    @Step("Check that 'Launchers Query' status is '{status}'")
    public WidgetEditForm checkThatLaunchersQueryStatusIs(String status) {
        launchersQueryStatus.shouldHave(text(status));
        return this;
    }

    @Step("Check that 'Launchers Query' count is '{count}'")
    public WidgetEditForm checkThatLaunchersQueryCountIs(String count) {
        launchersQueryCount.shouldHave(text(count));
        return this;
    }
    //endregion

    //region Select item steps
    @Step("Select item '{itemName}' from drop-down list 'Type'")
    public WidgetEditForm selectTypeItem(FormTypeItem itemName) {
        typeSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Metric'")
    public WidgetEditForm selectLaunchAnalyticMetricItem(FormLaunchAnalyticMetricItem itemName) {
        metricSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Metric'")
    public WidgetEditForm selectTopTestCasesMetricItem(FormTopTestCasesMetricItem itemName) {
        metricSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Group By'")
    public WidgetEditForm selectGroupByItem(FormGroupByItem itemName) {
        groupBySelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Tree'")
    public WidgetEditForm selectTreeItem(FormTreeItem itemName) {
        treeSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }
    //endregion

    //region Form button steps
    @Step("Click close button on form")
    public WidgetEditForm clickCloseButton() {
        getCloseButton().click();
        return this;
    }

    @Step("Click submit button on form")
    public WidgetEditForm clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click cancel button on form")
    public WidgetEditForm clickCancelButton() {
        cancelButton.click();
        return this;
    }
    //endregion
}
