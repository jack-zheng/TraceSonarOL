package sorra.tracesonarol.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraceServiceTest {
    @Autowired
    TraceService service;

    @Test
    public void test_query() {
        System.out.println(service.query());
        assertNotNull(service.query());
    }

    @Test
    public void test_query02() {
        System.out.println(service.query02());
    }
}