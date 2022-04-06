package cloud.autotests.api.project;

import cloud.autotests.api.base.ResponseDto;
import lombok.Getter;

@Getter
public class ProjectResponseDto implements ResponseDto {

    private Integer id;

    private String name;

    private Boolean isPublic;

    public static ProjectResponseDto fromJson(String json) {
        return gson.fromJson(json, ProjectResponseDto.class);
    }

    public static ProjectResponseDto[] fromJsonToArray(String json) {
        return gson.fromJson(json, ProjectResponseDto[].class);
    }

}
