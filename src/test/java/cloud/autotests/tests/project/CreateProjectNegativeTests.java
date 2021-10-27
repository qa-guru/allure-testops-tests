package cloud.autotests.tests.project;

import cloud.autotests.config.App;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Project tests")
public class CreateProjectNegativeTests extends TestBase {
    @WithLogin
    @Test
    void projectCantBeCreatedWithEmptyName() {
        String projectName = "";
        String errorMessage = "Name is required";

        projectsListPage
                .openPage()
                .createNewProjectWithError(projectName);

        projectsListPage.getCreateProjectPopup().checkErrorMessage(errorMessage);
    }
}
