package cloud.autotests.api.authorization;

import cloud.autotests.api.base.ResponseDto;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class AuthorizationResponseDto implements ResponseDto {

    @SerializedName("expires_in")
    private Integer expiresIn;

    @SerializedName("token_type")
    private String tokenType;

    private String scope;

    @SerializedName("access_token")
    private String accessToken;

    private String jti;

    public static AuthorizationResponseDto fromJson(String json) {
        return gson.fromJson(json, AuthorizationResponseDto.class);
    }

}
