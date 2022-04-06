package cloud.autotests.api.testCase;

import cloud.autotests.api.base.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TestCaseInfoResponseDto implements ResponseDto {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("projectId")
    private Integer projectId;

    @SerializedName("id")
    private Integer id;

    public static TestCaseInfoResponseDto fromJson(String json) {
        return gson.fromJson(json, TestCaseInfoResponseDto.class);
    }

}
