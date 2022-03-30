package cloud.autotests.api.testCase;

import cloud.autotests.api.interfaces.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class TestCaseInfoResponseDto implements ResponseDto {

    @SerializedName("name")
    private final String testCaseName;

    @SerializedName("projectId")
    private final Integer projectId;

    @SerializedName("id")
    private final Integer testCaseId;

    public static TestCaseInfoResponseDto fromJson(String json) {
        return gson.fromJson(json, TestCaseInfoResponseDto.class);
    }

}
