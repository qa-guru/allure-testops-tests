package cloud.autotests.helpers.extensions;

import cloud.autotests.api.project.CreateProjectRequestDto;
import cloud.autotests.api.project.ProjectApi;
import cloud.autotests.api.project.ProjectResponseDto;
import cloud.autotests.utilities.ProjectGenerator;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

public class ProjectExtension implements ParameterResolver, AfterTestExecutionCallback {

    private final ProjectApi projectApi = new ProjectApi();
    public static final ExtensionContext.Namespace NAMESPACE = create(ProjectExtension.class);

    @Override
    public void afterTestExecution(ExtensionContext context) {
        ProjectResponseDto project =
                context.getStore(NAMESPACE).get(context.getRequiredTestMethod().hashCode(), ProjectResponseDto.class);
        projectApi.deleteProject(project.getId());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRequiredTestMethod().isAnnotationPresent(WithProject.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(ProjectResponseDto.class);
    }

    @Override
    public ProjectResponseDto resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        WithProject createProject = extensionContext.getRequiredTestMethod().getAnnotation(WithProject.class);

        if (Objects.isNull(createProject)) {
            throw new ParameterResolutionException("WithProject annotation must be applied on the test method");
        }

        CreateProjectRequestDto generatedProject = ProjectGenerator.generateProject(createProject.isPublic());
        ProjectResponseDto project = projectApi.createProject(generatedProject);

        assertThat(project).isNotNull();
        extensionContext.getStore(NAMESPACE).put(extensionContext.getRequiredTestMethod().hashCode(), project);

        return project;
    }
}
