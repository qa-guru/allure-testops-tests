package cloud.autotests.api.testCaseTag;

public class TestCaseTagDtoBuilder {

    private final TestCaseTagDto testCaseTagDto = new TestCaseTagDto();

    public static TestCaseTagDtoBuilder builder() {
        return new TestCaseTagDtoBuilder();
    }

    TestCaseTagDtoBuilder setTagId(int tagId) {
        testCaseTagDto.setTagId(tagId);
        return this;
    }

    TestCaseTagDtoBuilder setTagName(String tagName) {
        testCaseTagDto.setTagName(tagName);
        return this;
    }

    public TestCaseTagDto build() {
        return testCaseTagDto;
    }

}
