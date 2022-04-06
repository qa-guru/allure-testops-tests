package cloud.autotests.api.dashboard;

import cloud.autotests.api.base.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CreateDashboardRequestDto implements RequestDto {

    @Expose
    private final String name;

    @Expose
    private final Integer projectId;

    @Override
    public String toJson() {
        return gson.toJson(this);
    }

}
