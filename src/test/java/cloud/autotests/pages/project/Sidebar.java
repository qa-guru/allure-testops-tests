package cloud.autotests.pages.project;

import cloud.autotests.data.MenuItem;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Sidebar {

    private final SelenideElement self = $(".SideMenu");
    private final ElementsCollection menuItems = self.$$(".SideMenu__nav-item");
    private final ElementsCollection userInfoItems = $$(".Menu__item");

    @Step("Navigate to menu item [{menuName}]")
    public void navigateTo(MenuItem menuName) {
        menuItems.find(attribute("aria-label", menuName.getDisplayedName()))
                .click();
    }

    @Step("Sign out from account")
    public void signOut() {
        navigateTo(MenuItem.USER_MENU);
        userInfoItems.find(text("Sign out")).click();
    }

    @Step("Navigate to user profile")
    public void navigateToUserProfile() {
        navigateTo(MenuItem.USER_MENU);
        userInfoItems.find(text("Your profile")).click();
    }

    @Step("Verify user successful authorization as [{username}]")
    public void checkThatUserAuthorizedAs(String username) {
        navigateTo(MenuItem.USER_MENU);
        userInfoItems.shouldHave(itemWithText("Signed in as \n" + username));
    }

}
