package cloud.autotests.api.testCaseTag;

import cloud.autotests.api.base.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class TestCaseTagDto implements RequestDto {

    public static TestCaseTagDto fromJson(String json) {
        return gson.fromJson(json, TestCaseTagDto.class);
    }

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Override
    public String toJson() {
        return gson.toJson(this);
    }

}
