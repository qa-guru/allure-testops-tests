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
                .filter(tag -> tag.getName().equals(tagName))
                .findFirst();
        if (optionalTag.isPresent())
            return optionalTag.get().getId();

        throw new RuntimeException("Don't found tag with name " + tagName);
    }

    public static void deleteTestCaseTagId(int tagId) {
        given().spec(defaultRequestSpec)
                .pathParam("id", tagId)
                .when()
                .delete(EndPoints.TEST_TAG)
                .then()
                .statusCode(204);
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
