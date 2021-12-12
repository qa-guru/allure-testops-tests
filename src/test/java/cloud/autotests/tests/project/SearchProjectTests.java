package cloud.autotests.tests.project;

import cloud.autotests.api.models.CreateProjectResponse;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.helpers.extensions.annotations.CreateProject;
import cloud.autotests.pages.ProjectsListPage;
import cloud.autotests.tests.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Owner("Denis")
@Story("Search project")
@DisplayName("Search project")
class SearchProjectTests extends TestBase {

    @Test
    @WithLogin
    @CreateProject(isPublic = true)
    @DisplayName("Find project by the project name with visibility true")
    void findProjectByName(CreateProjectResponse project) {
        ProjectsListPage projectPage = open("/", ProjectsListPage.class);
        projectPage.typeProjectNameIntoSearchField(project.getName())
                .checkTheProjectIsDisplayed(project.getName());
    }

    @Test
    @WithLogin
    @CreateProject(isPublic = false)
    @DisplayName("Find project by the project name with visibility false")
    void findProjectByNameWithVisibilityFalse(CreateProjectResponse project) {
        ProjectsListPage projectPage = open("/", ProjectsListPage.class);
        projectPage.typeProjectNameIntoSearchField(project.getName())
                .checkTheProjectIsDisplayed(project.getName());
    }

    @Test
    @WithLogin
    @DisplayName("Find project by none existent project name")
    void findProjectByNoneExistentName() {
        ProjectsListPage projectPage = open("/", ProjectsListPage.class);
        projectPage.typeProjectNameIntoSearchField(new Faker().funnyName().name())
                .verifyNoProjectsFoundMessage();
    }
}
