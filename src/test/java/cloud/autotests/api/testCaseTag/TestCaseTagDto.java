package cloud.autotests.api.testCaseTag;

import cloud.autotests.api.base.BaseDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestCaseTagDto extends BaseDto {

    public static TestCaseTagDto fromJson(String json) {
        return GSON.fromJson(json, TestCaseTagDto.class);
    }

    @Expose
    @SerializedName("id")
    private Integer tagId;
    public int getTagId() {
        return tagId;
    }
    void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Expose
    @SerializedName("name")
    private String tagName;
    public String getTagName() {
        return tagName;
    }
    void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toJson() {
        return GSON.toJson(this);
    }

}
