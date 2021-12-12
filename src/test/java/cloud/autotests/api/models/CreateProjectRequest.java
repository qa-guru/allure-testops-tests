package cloud.autotests.api.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true)
public class CreateProjectRequest {

    @JsonProperty("isPublic")
    private boolean isPublic;
    private String name;
    private String abbr;
    private String description;
}
