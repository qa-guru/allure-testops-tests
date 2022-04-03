package cloud.autotests.api.project;

import cloud.autotests.api.base.BaseDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectDto extends BaseDto {

    public static ProjectDto fromJson(String json) {
        return GSON.fromJson(json, ProjectDto.class);
    }

    @Expose
    @SerializedName("name")
    private String projectName;
    public String getProjectName() {
        return projectName;
    }
    void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Expose
    private Boolean isPublic;
    public boolean getIsPublic() {
        return isPublic;
    }
    void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String toJson() {
        return GSON.toJson(this);
    }

}
