package cloud.autotests.api.defect;

import cloud.autotests.api.interfaces.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CreateDefectRequestDto implements RequestDto {

    @Expose
    private final String name;

    @Expose
    private final String description;

    @Expose
    private final Integer projectId;

    @Override
    public String toJson() {
        return gson.toJson(this);
    }

}
