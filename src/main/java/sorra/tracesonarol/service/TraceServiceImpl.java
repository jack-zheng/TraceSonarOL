package sorra.tracesonarol.service;

import org.springframework.stereotype.Service;
import sorra.tracesonar.core.Traceback;
import sorra.tracesonar.model.Query;

import java.util.Collections;

@Service
public class TraceServiceImpl implements TraceService{
    @Override
    public String query() {
        CharSequence output = new Traceback(true, true, true).run(new Query("sorra/tracesonar/sample/modifier/Private", "*", "*"), Collections.emptyList());
        return output.toString();
    }
}
