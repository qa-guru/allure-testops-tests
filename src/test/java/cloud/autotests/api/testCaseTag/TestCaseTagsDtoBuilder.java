package cloud.autotests.api.testCaseTag;

public class TestCaseTagsDtoBuilder {

    private final TestCaseTagsDto tags = new TestCaseTagsDto();

    public static TestCaseTagsDtoBuilder builder() {
        return new TestCaseTagsDtoBuilder();
    }

    public TestCaseTagsDtoBuilder addTag(TestCaseTagDto tag) {
        tags.addTag(tag);
        return this;
    }

    public TestCaseTagsDto build() {
        return tags;
    }

}
