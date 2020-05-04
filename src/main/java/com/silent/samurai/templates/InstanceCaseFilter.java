package com.silent.samurai.templates;

import com.google.common.base.CaseFormat;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.filter.Filter;

public class InstanceCaseFilter implements Filter {

    @Override
    public String filter(Object var, JinjavaInterpreter jinjavaInterpreter, String... args) {

        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, var.toString());
    }

    @Override
    public String getName() {
        return "instance";
    }
}
