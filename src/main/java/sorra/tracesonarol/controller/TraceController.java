package sorra.tracesonarol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sorra.tracesonarol.service.TraceService;

@RestController
public class TraceController {
    @Autowired
    private TraceService traceService;

    @GetMapping("/query")
    public String query() {
        return traceService.query();
    }
}

