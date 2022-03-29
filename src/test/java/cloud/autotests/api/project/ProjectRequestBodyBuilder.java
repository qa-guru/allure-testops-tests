package cloud.autotests.api.project;

public class ProjectRequestBodyBuilder {

    private final ProjectRequestBody projectRequestBody = new ProjectRequestBody();

    public static ProjectRequestBodyBuilder builder() {
        return new ProjectRequestBodyBuilder();
    }

    public ProjectRequestBodyBuilder addProjectName(String projectName) {
        projectRequestBody.setProjectName(projectName);
        return this;
    }

    public ProjectRequestBodyBuilder addIsPublic(boolean isPublic) {
        projectRequestBody.setIsPublic(isPublic);
        return this;
    }

    public ProjectRequestBody build() {
        return projectRequestBody;
    }

}
