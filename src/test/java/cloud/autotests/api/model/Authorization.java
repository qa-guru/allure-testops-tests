package cloud.autotests.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authorization {

    @JsonProperty("access_token")
    private String access_token;

    @JsonProperty("token_type")
    private String token_type;

    @JsonProperty("refresh_token")
    private String refresh_token;

    @JsonProperty("expires_in")
    private String expires_in;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("jti")
    private String jti;

    public String asString() throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(this);
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

    public String getJti() {
        return jti;
    }
}
