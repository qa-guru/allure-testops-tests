package cloud.autotests.tests;

import lombok.Getter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DelMe {

    static class A {
        @Getter
        private Integer age = 123;
    }

    @Test
    @Tag("test123")
    void test01() {
        A a = new A();
        System.out.println(a.getAge());

    }


}
