package cloud.autotests.api.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface RequestDto {

    // Only exposed (@Expose) variables are to be serialized/deserialized
    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    String toJson();

}
