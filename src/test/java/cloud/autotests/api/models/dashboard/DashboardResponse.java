package cloud.autotests.api.models.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true)
public class DashboardResponse {
    private long createdDate;
    private long lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private String name;
    private int id;
    private int projectId;
}