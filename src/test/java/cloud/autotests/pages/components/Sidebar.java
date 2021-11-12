package cloud.autotests.pages.components;

import cloud.autotests.data.MenuItem;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class Sidebar {

    private SelenideElement self = $(".SideMenu");
    private ElementsCollection menuItems = self.$$(".SideMenu__nav-item");

    @Step("Navigate to menu item `{menuName}`")
    public void navigateTo(MenuItem menuName) {
        menuItems.find(text(menuName.getDisplayedName())).click();
    }

}
