package cloud.autotests.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboard {

    @JsonProperty("id")
    int id;

    @JsonProperty("projectId")
    int projectId;

    @JsonProperty("name")
    String name;

    public int getId() {
        return id;
    }

    public Dashboard setId(int id) {
        this.id = id;
        return this;
    }

    public int getProjectId() {
        return projectId;
    }

    public Dashboard setProjectId(int projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dashboard setName(String name) {
        this.name = name;
        return this;
    }
}
