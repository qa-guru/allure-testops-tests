package cloud.autotests.api.project;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ProjectRequestBody {

    @SerializedName("name")
    private String projectName;
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private Boolean isPublic;
    public boolean getIsPublic() {
        return isPublic;
    }
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
