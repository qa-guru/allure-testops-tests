package cloud.autotests.api.testCase;

import cloud.autotests.api.interfaces.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TestCaseInfoResponseDto implements ResponseDto {

    @SerializedName("name")
    private String testCaseName;

    @SerializedName("projectId")
    private Integer projectId;

    @SerializedName("id")
    private Integer testCaseId;

    public static TestCaseInfoResponseDto fromJson(String json) {
        return gson.fromJson(json, TestCaseInfoResponseDto.class);
    }

}
