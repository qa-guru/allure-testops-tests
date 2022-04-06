package cloud.autotests.api.base;

import cloud.autotests.api.authorization.AuthorizationApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class BaseApi {

    protected final static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .setContentType(JSON)
            .addHeader("Authorization", "Bearer " + AuthorizationApi.getAuthorization().getAccessToken())
            .build();

}
