package com.silent.samurai.templates;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.filter.Filter;

public class InstanceCaseFilter implements Filter {

    @Override
    public String filter(Object var, JinjavaInterpreter jinjavaInterpreter, String... args) {

        return Character.toLowerCase(var.toString().charAt(0)) + var.toString().substring(1);
    }

    @Override
    public String getName() {
        return "instance";
    }
}
