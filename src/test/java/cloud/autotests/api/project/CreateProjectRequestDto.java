package cloud.autotests.api.project;

import cloud.autotests.api.base.RequestDto;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CreateProjectRequestDto implements RequestDto {

    @Expose
    private final String name;

    @Expose
    private final Boolean isPublic;

    @Override
    public String toJson() {
        return gson.toJson(this);
    }

}
