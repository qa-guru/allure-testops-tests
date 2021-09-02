package cloud.autotests.tests;

import cloud.autotests.config.App;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;



public class addTagTestRaw extends TestBase{
    addTagTestRaw addTagTestRaw = new addTagTestRaw();


public static void OpenPage()  {
                open("");
    }

    public static void FillLoginForm() {
            $(byName("username")).setValue(App.config.userLogin());
            $(byName("password")).setValue((App.config.userPassword())).pressEnter();
        }

    public static void  FillProjectname() {
            $(".Input").val("GOST_group_tests").pressEnter();
        }

    public static void GoToTestCases() {
            $(".link:nth-child(2) > .ProjectCard__link-text").click();
        }

    public static void addNewTag() {
            $(".LoadableTreeControlPanel__checkbox-wrapper > .Checkbox__custom-input").click();
            $(".LoadableTreeControlPanel > .Button").click();
            $(".tippy-content").$(byText("Add tags")).click();
            $("#react-select-3-input").val("ref").pressEnter();
            $(".Button_style_success").click();
        }
    public static void AssertTagAdd() {
            $(byText("4053")).click();
            $$(".TestCaseOverview__secondary").find(text("Tags")).should(text("ref"));
        }
    }