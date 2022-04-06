package cloud.autotests.api.testPlan;

import cloud.autotests.api.base.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TestPlanResponseDto implements ResponseDto {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("projectId")
    private Integer projectId;

    public static TestPlanResponseDto fromJson(String json) {
        return gson.fromJson(json, TestPlanResponseDto.class);
    }

}
