package cloud.autotests.api.dashboard;

import cloud.autotests.api.interfaces.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class DashboardResponseDto implements ResponseDto {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("projectId")
    private Integer projectId;

    public static DashboardResponseDto fromJson(String json) {
        return gson.fromJson(json, DashboardResponseDto.class);
    }

}
