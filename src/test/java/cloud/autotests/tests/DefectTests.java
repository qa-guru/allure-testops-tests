package cloud.autotests.tests;

import cloud.autotests.api.defect.CreateDefectRequestDto;
import cloud.autotests.api.defect.DefectApi;
import cloud.autotests.data.DefectStatus;
import cloud.autotests.helpers.WithLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cloud.autotests.data.DefectActionMenuItem.*;

public class DefectTests extends TestBase {

    // Test project [dont-remove-autotests-for-defects]
    private final static int PROJECT_ID = 1157;
    private final CreateDefectRequestDto defect = CreateDefectRequestDto.builder()
            .name(faker.random().hex(15))
            .description(faker.random().hex(20))
            .projectId(PROJECT_ID)
            .build();
    private Integer createdDefectId;

    @AfterEach
    void deleteCreatedDefect() {
        // Cleaning data
        // createdDefectId != null, значит в рамках теста создан новый дефект, который необходимо подчистить
        if (createdDefectId != null)
            DefectApi.deleteDefect(createdDefectId);
    }

    @Test
    @WithLogin
    @DisplayName("Create defect")
    void createDefect() {
        // Arrange
        defectsListPage.openPage(PROJECT_ID);

        // Act
        defectsListPage.createNewDefect(defect.getName(), defect.getDescription());
        createdDefectId = defectPage.getDefectId();

        // Assert
        defectsListPage.checkThatDefectsListContainsDefect(defect.getName());
        defectPage.checkThatDefectNameIs(defect.getName());
        defectPage.checkThatDefectDescriptionIs(defect.getDescription());
        defectPage.checkThatDefectStatusIs(DefectStatus.OPEN);
    }

    @Test
    @WithLogin
    @DisplayName("Edit defect")
    void editDefect() {
        // Test data
        String newName = faker.random().hex(15);
        String newDescription = faker.random().hex(20);

        // Arrange
        createdDefectId = DefectApi.createDefect(defect).getDefectId();
        defectPage.openPage(PROJECT_ID, createdDefectId);

        // Act
        defectPage.editDefectName(newName);
        defectPage.editDefectDescription(newDescription);
        defectPage.editDefectStatus(CLOSE.getDisplayedName());

        // Assert
        defectPage.checkThatDefectNameIs(newName);
        defectPage.checkThatDefectDescriptionIs(newDescription);
        defectPage.checkThatDefectStatusIs(DefectStatus.CLOSED);
    }

    @Test
    @WithLogin
    @DisplayName("Delete defect")
    void deleteDefect() {
        // Arrange
        createdDefectId = DefectApi.createDefect(defect).getDefectId();
        defectPage.openPage(PROJECT_ID, createdDefectId);

        // Act
        defectPage.deleteDefect();

        // Assert
        defectsListPage.checkThatDefectsListDoNotContainsDefect(defect.getName());

        // Присваиваю createdDefectId = null,
        // тк удалять дефект через API (в @AfterEach) не нужно (потому что мы его удалили через UI)
        createdDefectId = null;
    }

}
