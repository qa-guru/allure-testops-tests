package cloud.autotests.tests.testcase;

import cloud.autotests.helpers.WithLogin;
import cloud.autotests.tests.TestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("crud")
public class CommentTests extends TestBase {

    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";

    @WithLogin
    @Test
    @Disabled
    void addCommentTest() {
        // create project by api
        // create testcase by api
        // open testcase in ui
        // add comment in ui
        // check  comment
        // delete comment, testcase, project by api
    }

    void editCommentTest() {
        // create project by api
        // create testcase by api
        // add comment by api
        // open testcase in ui
        // edit comment in ui
        // check comment
        // delete comment, testcase, project by api
    }

    void deleteCommentTest() {
        // create project by api
        // create testcase by api
        // add comment by api
        // open testcase in ui
        // delete comment in ui
        // check comment
        // delete comment, testcase, project by api
    }
}
