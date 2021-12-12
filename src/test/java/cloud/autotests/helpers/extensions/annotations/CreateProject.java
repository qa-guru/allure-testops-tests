package cloud.autotests.helpers.extensions.annotations;

import cloud.autotests.helpers.extensions.ProjectExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(ProjectExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CreateProject {
    boolean isPublic();
}
