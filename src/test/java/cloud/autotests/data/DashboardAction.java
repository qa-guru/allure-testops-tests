package cloud.autotests.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DashboardAction {
    DELETE_DASHBOARD(" Delete dashboard");

    String name;
}
