package cloud.autotests.helpers.extensions;

import cloud.autotests.api.Project;
import cloud.autotests.api.models.CreateProjectRequest;
import cloud.autotests.api.models.CreateProjectResponse;
import cloud.autotests.helpers.extensions.annotations.CreateProject;
import cloud.autotests.utilities.ProjectGenerator;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

public class ProjectExtension implements ParameterResolver, AfterEachCallback {

    private Project projectService = new Project();
    public static final ExtensionContext.Namespace NAMESPACE = create(ProjectExtension.class);

    @Override
    public void afterEach(ExtensionContext context) {
        CreateProjectResponse project =
                context.getStore(NAMESPACE).get(context.getRequiredTestMethod().hashCode(), CreateProjectResponse.class);
        projectService.removeProjectById(project.getId());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRequiredTestMethod().isAnnotationPresent(CreateProject.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(CreateProjectResponse.class);
    }

    @Override
    public CreateProjectResponse resolveParameter(ParameterContext parameterContext,
                                                  ExtensionContext extensionContext) throws ParameterResolutionException {
        CreateProject createProject = extensionContext.getRequiredTestMethod().getAnnotation(CreateProject.class);

        if (Objects.isNull(createProject)) {
            throw new ParameterResolutionException("CreateProject annotation should be applied on the test method");
        }

        final CreateProjectRequest generatedProject = ProjectGenerator.generateProject(createProject.isPublic());

        CreateProjectResponse project = projectService.createProject(generatedProject);

        assertThat(project).isNotNull();

        extensionContext.getStore(NAMESPACE).put(extensionContext.getRequiredTestMethod().hashCode(), project);

        return project;
    }
}
