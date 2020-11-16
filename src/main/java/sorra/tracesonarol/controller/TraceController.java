package sorra.tracesonarol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sorra.tracesonarol.service.TraceService;

@Controller
public class TraceController {
    @Autowired
    private TraceService traceService;

    @GetMapping("/query")
    @ResponseBody
    public String query() {
        return traceService.query();
    }

    @GetMapping("/objtojson")
    @ResponseBody
    public String objToJson() {
        return traceService.query02();
    }

    @GetMapping("/tree")
    public String getTestPage() {
        return "tree";
    }
}

