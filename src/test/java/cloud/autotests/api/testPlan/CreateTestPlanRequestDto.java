package cloud.autotests.api.testPlan;

import cloud.autotests.api.interfaces.RequestDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CreateTestPlanRequestDto implements RequestDto {

    @Expose
    @SerializedName("name")
    private final String name;

    @Expose
    @SerializedName("projectId")
    private final Integer projectId;

    @Override
    public String toJson() {
        return gson.toJson(this);
    }

}
