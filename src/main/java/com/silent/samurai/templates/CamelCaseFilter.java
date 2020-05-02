package com.silent.samurai.templates;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.filter.Filter;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.text.CaseUtils;

public class CamelCaseFilter implements Filter {

    @Override
    public String filter(Object var, JinjavaInterpreter jinjavaInterpreter, String... args) {
        boolean capitalizeFirstLetter = true;
        if (args.length > 0) {
            capitalizeFirstLetter = BooleanUtils.toBoolean(args[0]);
        }
        return CaseUtils.toCamelCase(var.toString(), capitalizeFirstLetter);
    }

    @Override
    public String getName() {
        return "camel";
    }
}
