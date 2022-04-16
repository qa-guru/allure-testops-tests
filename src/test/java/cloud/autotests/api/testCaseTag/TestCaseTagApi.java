package cloud.autotests.api.testCaseTag;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.endpoint.TestCaseTagEndPoint;
import com.google.gson.Gson;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class TestCaseTagApi extends BaseApi {

    public Integer getTestCaseTagId(int testCaseId, String tagName) {
        String json = getTestCaseTags(testCaseId).asString();
        TestCaseTagDto[] tags = new Gson().fromJson(json, TestCaseTagDto[].class);

        Optional<TestCaseTagDto> optionalTag = Arrays.stream(tags)
                .filter(tag -> tag.getName().equals(tagName))
                .findFirst();
        if (optionalTag.isPresent())
            return optionalTag.get().getId();

        throw new RuntimeException("Don't found tag with name " + tagName);
    }

    public void deleteTestCaseTagId(int tagId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", tagId)
                .when()
                    .delete(TestCaseTagEndPoint.DELETE)
                .then()
                    .statusCode(204);
    }

    public TestCaseTagDto createNewTestCaseTag(String newTagName) {
        TestCaseTagDto tag = TestCaseTagDto.builder()
                .name(newTagName)
                .build();

        String json = given().spec(defaultRequestSpec)
                    .body(tag.toJson())
                .when()
                    .post(TestCaseTagEndPoint.CREATE)
                .then()
                    .statusCode(200)
                    .extract().asString();

        return TestCaseTagDto.fromJson(json);
    }

    public void setTestCaseTags(int testCaseId, TestCaseTagDto... tags) {
        TestCaseTagsDtoBuilder builder = TestCaseTagsDtoBuilder.builder();
        for (TestCaseTagDto tag : tags)
            builder.addTag(tag);

        given().spec(defaultRequestSpec)
                    .pathParam("testCaseId", testCaseId)
                    .body(builder.build().toJson())
                .when()
                    .post(TestCaseTagEndPoint.SET_BY_TEST_CASE)
                .then()
                    .statusCode(200)
                    .extract()
                    .response();
    }

    private Response getTestCaseTags(int testCaseId) {
        return given().spec(defaultRequestSpec)
                .pathParam("testCaseId", testCaseId)
                .when()
                .get(TestCaseTagEndPoint.FIND_BY_TEST_CASE)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
