package sorra.tracesonarol.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import sorra.tracesonar.core.Searcher;
import sorra.tracesonar.core.Traceback;
import sorra.tracesonar.core.TreeNode;
import sorra.tracesonar.model.Query;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TraceServiceImpl implements TraceService{
    @Override
    public String query() {
        CharSequence output = new Traceback(true, true, true).run(new Query("sorra/tracesonar/sample/modifier/Private", "*", "*"), Collections.emptyList());
        return output.toString();
    }

    @Override
    public String query02() {
        Searcher searcher = new Searcher(true, true, Collections.emptyList());
        Stream<TreeNode> nodeStream =  searcher.search(new Query("sorra/tracesonar/sample/modifier/Private", "*", "*"));
        List<TreeNode> nodeList = nodeStream.collect(Collectors.toList());
        return JSON.toJSONString(nodeList);
    }
}
