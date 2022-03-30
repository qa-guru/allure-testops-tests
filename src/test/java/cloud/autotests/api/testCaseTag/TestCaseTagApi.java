package cloud.autotests.api.testCaseTag;

import cloud.autotests.api.BaseApi;
import cloud.autotests.api.EndPoints;
import com.google.gson.Gson;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class TestCaseTagApi extends BaseApi {

    public static Integer getTestCaseTagId(int testCaseId, String tagName) {
        String json = getTestCaseTags(testCaseId).asString();
        TestCaseTagDto[] tags = new Gson().fromJson(json, TestCaseTagDto[].class);

        Optional<TestCaseTagDto> optionalTag = Arrays.stream(tags)
                .filter(tag -> tag.getTagName().equals(tagName))
                .findFirst();
        if (optionalTag.isPresent())
            return optionalTag.get().getTagId();

        throw new RuntimeException("Don't found tag with name " + tagName);
    }

    public static void deleteTestCaseTagId(int tagId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", tagId)
                .when()
                    .delete(EndPoints.TEST_TAG_BY_ID)
                .then()
                    .statusCode(204);
    }

    public static TestCaseTagDto createNewTestCaseTag(String newTagName) {
        TestCaseTagDto tag = TestCaseTagDtoBuilder.builder()
                .setTagName(newTagName)
                .build();

        String json = given().spec(defaultRequestSpec)
                    .body(tag.toJson())
                .when()
                    .post(EndPoints.TEST_TAG)
                .then()
                    .statusCode(200)
                    .extract().asString();

        return TestCaseTagDto.fromJson(json);
    }

    public static void setTestCaseTags(int testCaseId, TestCaseTagDto... tags) {
        TestCaseTagsDtoBuilder builder = TestCaseTagsDtoBuilder.builder();
        for (TestCaseTagDto tag : tags)
            builder.addTag(tag);

        given().spec(defaultRequestSpec)
                    .pathParam("testCaseId", testCaseId)
                    .body(builder.build().toJson())
                .when()
                    .post(EndPoints.TEST_CASE_TAG)
                .then()
                    .statusCode(200)
                    .extract()
                    .response();
    }

    private static Response getTestCaseTags(int testCaseId) {
        return given().spec(defaultRequestSpec)
                .pathParam("testCaseId", testCaseId)
                .when()
                .get(EndPoints.TEST_CASE_TAG)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
