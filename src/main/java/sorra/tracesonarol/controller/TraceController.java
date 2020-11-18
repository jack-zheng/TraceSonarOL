package sorra.tracesonarol.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sorra.tracesonar.core.Searcher;
import sorra.tracesonar.core.TreeNode;
import sorra.tracesonar.model.Query;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TraceController {

    @GetMapping("/query/{queryStr}")
    @ResponseBody
    public String objToJson(@PathVariable("queryStr") String query) {
        System.out.println(query);
        String[] ret = query.split("#");
        String owner = ret[0].replace(".", "/");
        String method = ret[1];
        Stream<TreeNode> nodeStream = (new Searcher(true, true, Collections.emptyList())).search(new Query(owner, method, "*"));
        List<TreeNode> nodeList = nodeStream.collect(Collectors.toList());
        String jsonStr = JSON.toJSONString(nodeList);
        System.out.println(jsonStr);
        return jsonStr;
    }

    @GetMapping("/tree")
    public String getTestPage() {
        return "tree";
    }
}

