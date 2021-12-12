package cloud.autotests.utilities;

import cloud.autotests.api.models.CreateProjectRequest;
import com.github.javafaker.Faker;

public class ProjectGenerator {

    public static CreateProjectRequest generateProject(boolean projectVisibility) {
        String randomString = new Faker().name().name();
        return CreateProjectRequest.builder()
                .name(randomString)
                .abbr(randomString.substring(0, 2))
                .description(randomString)
                .isPublic(projectVisibility)
                .build();
    }
}
