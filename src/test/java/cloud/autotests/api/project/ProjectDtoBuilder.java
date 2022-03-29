package cloud.autotests.api.project;

public class ProjectDtoBuilder {

    private final ProjectDto projectDto = new ProjectDto();

    public static ProjectDtoBuilder builder() {
        return new ProjectDtoBuilder();
    }

    public ProjectDtoBuilder addProjectName(String projectName) {
        projectDto.setProjectName(projectName);
        return this;
    }

    public ProjectDtoBuilder addIsPublic(boolean isPublic) {
        projectDto.setIsPublic(isPublic);
        return this;
    }

    public ProjectDto build() {
        return projectDto;
    }

}
