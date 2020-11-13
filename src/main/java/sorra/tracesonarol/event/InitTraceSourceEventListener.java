package sorra.tracesonarol.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sorra.tracesonar.core.FileWalker;
import sorra.tracesonar.core.QualifierFilter;
import sorra.tracesonarol.pojo.InitBean;

import java.util.Collections;

@Component
public class InitTraceSourceEventListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(InitTraceSourceEventListener.class);

    @Autowired
    InitBean bean;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        FileWalker.walkAll(bean.getPaths(), new QualifierFilter(Collections.emptyList(), Collections.emptyList()));
        logger.info("Finish fill ClassMap and GraphStore...");
    }
}
