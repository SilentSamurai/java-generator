package com.silent.samurai;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

public class JinjaTemplate {

    static final Logger logger = Logger.getLogger(JinjaTemplate.class);

    public static void template() throws IOException {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("name", "Jared");

        String template = Resources.toString(Resources.getResource("templates/controller.jt"), Charsets.UTF_8);

        String renderedTemplate = jinjava.render(template, context);

        logger.info(renderedTemplate);

    }
}
