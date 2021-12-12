package cloud.autotests.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true)
public class CreateProjectResponse  {

    @JsonProperty("isPublic")
    private boolean isPublic;
    private int id;
    private String name;
    private String abbr;
    private String description;
    private String descriptionHtml;
    private long createdDate;
    private long lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
}
