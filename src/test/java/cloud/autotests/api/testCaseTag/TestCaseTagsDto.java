package cloud.autotests.api.testCaseTag;

import cloud.autotests.api.base.BaseDto;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class TestCaseTagsDto extends BaseDto {

    public static TestCaseTagsDto fromJson(String json) {
        return GSON.fromJson(json, TestCaseTagsDto.class);
    }

    @Expose
    private final List<TestCaseTagDto> tags = new ArrayList<>();

    public List<TestCaseTagDto> getTags() {
        return tags;
    }

    void addTag(TestCaseTagDto tag) {
        tags.add(tag);
    }

    @Override
    public String toJson() {
        return GSON.toJson(tags.toArray());
    }

}
