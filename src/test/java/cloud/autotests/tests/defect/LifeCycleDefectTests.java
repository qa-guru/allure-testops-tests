package cloud.autotests.tests.defect;

import cloud.autotests.data.DefectStatus;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.models.Defect;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsListPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.defects.DefectsPage;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class LifeCycleDefectTests extends TestBase {

    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";
    private ProjectsListPage projectsListPage = new ProjectsListPage();
    private ProjectsTable projectsTable = new ProjectsTable();
    private DefectsPage defectsPage = new DefectsPage();
    private Defect defectData = new Defect();

    @WithLogin
    @Test
    void createDefectCheckNameDescriptionStatus() {
        //todo открыть, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);

        //Выбираем нужный проект и переходим к его дефектам
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.DEFECTS);

        //Создаем новый дефект
        defectsPage = defectsPage.createNewDefect(defectData.getName(), defectData.getDescription());

        //Проверяем созданный дефект
        defectsPage.getDefectInformation().checkDefectName(defectData.getName());
        defectsPage.getDefectInformation().checkDefectDescription(defectData.getDescription());
        defectsPage.getDefectInformation().checkDefectStatus(DefectStatus.OPEN);
    }

    @WithLogin
    @Test
    void changeDefectStatus() {
        //todo открыть, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);

        //Выбираем нужный проект и переходим к его дефектам
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.DEFECTS);

        //Создаем новый дефект
        defectsPage = defectsPage.createNewDefect(defectData.getName(), defectData.getDescription());
        //Изменяем статус дефекта на Closed
        defectsPage.getDefectInformation().changeStatus();

        //Проверяем статус дефекта
        defectsPage.getDefectsList().navigateTo(defectData.getName());
        defectsPage.getDefectInformation().checkDefectStatus(DefectStatus.CLOSED);
    }

    @WithLogin
    @Test
    void changeDefectDescription() {
        //todo открыть, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);

        //Выбираем нужный проект и переходим к его дефектам
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.DEFECTS);

        //Создаем новый дефект
        defectsPage = defectsPage.createNewDefect(defectData.getName(), defectData.getDescription());
        //Изменяем описание дефекта;
        defectsPage.getDefectInformation().setChangeDescription(defectData.generateNewDescription());

        //Проверяем описание дефекта
        defectsPage.getDefectsList().navigateTo(defectData.getName());
        defectsPage.getDefectInformation().checkDefectDescription(defectData.getDescription());
    }

    @WithLogin
    @Test
    void deleteDefect() {
        //todo открыть, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);

        //Выбираем нужный проект и переходим к его дефектам
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.DEFECTS);

        //Создаем новый дефект
        defectsPage = defectsPage.createNewDefect(defectData.getName(), defectData.getDescription());
        //Удаляем созданный дефект
        defectsPage.getDefectsList().navigateTo(defectData.getName()).deleteDefect();

        //Проверяем, что дефекта нет в списке
        defectsPage.getDefectsList().checkDefectDeletion(defectData.getName());
    }

    @WithLogin
    @Test
    void renameDefect() {
        //todo открыть, когда api аитентификация заработает
        //ProjectsListPage projectsListPage = open("", ProjectsListPage.class);

        //Выбираем нужный проект и переходим к его дефектам
        projectsListPage.filterProject(PROJECT_NAME);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.DEFECTS);

        //Создаем новый дефект
        defectsPage = defectsPage.createNewDefect(defectData.getName(), defectData.getDescription());
        //Переименовываем созданный дефект
        defectsPage.getDefectsList().navigateTo(defectData.getName()).renameDefect(defectData.generateNewName());

        //Проверяем имя дефекта
        defectsPage.getDefectsList().navigateTo(defectData.getName());
        defectsPage.getDefectInformation().checkDefectName(defectData.getName());
    }

    @AfterEach
    @Step("Deleting defect after test")
    public void cleanAfterTest(){
        if(defectsPage.getDefectsList().isDefectInList(defectData.getName())){
            defectsPage.getDefectsList().navigateTo(defectData.getName()).deleteDefect();
        }
    }
}
