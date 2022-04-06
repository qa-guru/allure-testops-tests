package cloud.autotests.api.dashboard;

import cloud.autotests.api.base.ResponseDto;
import lombok.Getter;

@Getter
public class DashboardResponseDto implements ResponseDto {

    private Integer id;

    private String name;

    private Integer projectId;

    public static DashboardResponseDto fromJson(String json) {
        return gson.fromJson(json, DashboardResponseDto.class);
    }

}
