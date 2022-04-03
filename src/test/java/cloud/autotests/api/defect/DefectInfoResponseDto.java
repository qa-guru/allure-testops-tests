package cloud.autotests.api.defect;

import cloud.autotests.api.interfaces.ResponseDto;
import lombok.Getter;

@Getter
public class DefectInfoResponseDto implements ResponseDto {

    private Integer id;

    private String name;

    private String description;

    private Integer projectId;

    public static DefectInfoResponseDto fromJson(String json) {
        return gson.fromJson(json, DefectInfoResponseDto.class);
    }

}
