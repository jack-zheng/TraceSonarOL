package sorra.tracesonarol.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraceControllerTest {

    @Autowired
    private TraceController controller;

    @Test
    void query() {
        String ret = controller.query("com.successfactors.datamodel.util.SuccessionDataModelParser#parse");
        System.out.println(ret);
    }
}