package com.silent.samurai.templates;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class JinjaTemplate {

    static final Logger logger = Logger.getLogger(JinjaTemplate.class);

    static Jinjava jinjava;

    static {
        jinjava = new Jinjava();
        jinjava.getGlobalContext().registerFilter(new CamelCaseFilter());
        jinjava.getGlobalContext().registerFilter(new InstanceCaseFilter());
    }

    public static String render(String templateName, Map<String, Object> context) throws IOException {

//        Map<String, Object> context = Maps.newHashMap();
//        context.put("name", "Jared");

        String template = Resources.toString(Resources.getResource("templates/" + templateName), Charsets.UTF_8);

        String renderedTemplate = jinjava.render(template, context);

//        logger.info(renderedTemplate);
        return renderedTemplate;
    }
}
