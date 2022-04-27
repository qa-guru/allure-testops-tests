package cloud.autotests.api.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface RequestDto {

    Gson gson = new GsonBuilder().create();

    String toJson();

}
