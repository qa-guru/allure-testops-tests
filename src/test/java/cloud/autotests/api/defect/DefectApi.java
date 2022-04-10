package cloud.autotests.api.defect;

import cloud.autotests.api.base.BaseApi;
import cloud.autotests.api.endpoint.DefectEndPoint;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class DefectApi extends BaseApi {

    public static DefectInfoResponseDto createDefect(CreateDefectRequestDto defect) {
        String responseJson = given().spec(defaultRequestSpec)
                    .body(defect.toJson())
                .when()
                    .post(DefectEndPoint.CREATE)
                .then()
                    .statusCode(anyOf(is(200), is(204)))
                    .extract().response().asString();
        return DefectInfoResponseDto.fromJson(responseJson);
    }

    public static void deleteDefect(int defectId) {
        given().spec(defaultRequestSpec)
                    .pathParam("id", defectId)
                .when()
                    .delete(DefectEndPoint.DELETE)
                .then()
                    .statusCode(anyOf(is(200), is(204)));
    }

}
