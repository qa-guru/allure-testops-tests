package cloud.autotests.api.defect;

import cloud.autotests.api.interfaces.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class DefectInfoResponseDto implements ResponseDto {

    @SerializedName("id")
    private Integer defectId;

    @SerializedName("name")
    private String defectName;

    @SerializedName("description")
    private String description;

    @SerializedName("projectId")
    private Integer projectId;

    public static DefectInfoResponseDto fromJson(String json) {
        return gson.fromJson(json, DefectInfoResponseDto.class);
    }

}
