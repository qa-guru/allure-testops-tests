package cloud.autotests.api.testCaseTag;

import cloud.autotests.api.base.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class TestCaseTagsDto implements RequestDto {

    public static TestCaseTagsDto fromJson(String json) {
        return gson.fromJson(json, TestCaseTagsDto.class);
    }

    @Expose
    @Getter
    private final List<TestCaseTagDto> tags = new ArrayList<>();

    void addTag(TestCaseTagDto tag) {
        tags.add(tag);
    }

    public String toJson() {
        return gson.toJson(tags.toArray());
    }

}

