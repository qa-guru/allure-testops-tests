package cloud.autotests.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {
    String webUrl();
    String apiUrl();
    String userLogin();
    String userPassword();
    String userToken();
    int projectId();
}
