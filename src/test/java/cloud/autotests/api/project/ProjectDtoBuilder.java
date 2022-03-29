package cloud.autotests.api.project;

public class ProjectDtoBuilder {

    private final ProjectDto projectDto = new ProjectDto();

    public static ProjectDtoBuilder builder() {
        return new ProjectDtoBuilder();
    }

    public ProjectDtoBuilder setProjectName(String projectName) {
        projectDto.setProjectName(projectName);
        return this;
    }

    public ProjectDtoBuilder setIsPublic(boolean isPublic) {
        projectDto.setIsPublic(isPublic);
        return this;
    }

    public ProjectDto build() {
        return projectDto;
    }

}
