package cloud.autotests.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseDto {

    protected static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public abstract String toJson();

}
