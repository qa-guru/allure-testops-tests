package cloud.autotests.tests.specs;

import cloud.autotests.api.Authorization;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {

    public static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .log(LogDetail.BODY)
            .addHeader("Authorization", "Bearer " + new Authorization().getAccessToken())
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build();

    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .build();

}
