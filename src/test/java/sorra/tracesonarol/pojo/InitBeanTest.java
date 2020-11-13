package sorra.tracesonarol.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InitBeanTest {
    @Autowired
    InitBean bean;

    @Test
    public void test() {
        assertTrue(bean.getPaths().size() > 0);
    }
}