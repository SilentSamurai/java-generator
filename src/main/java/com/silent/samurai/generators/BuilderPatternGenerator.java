package com.silent.samurai.generators;

import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FieldContext;
import com.silent.samurai.templates.JinjaTemplate;
import com.silent.samurai.utils.ClassWriterUtils;
import com.silent.samurai.utils.FieldUtil;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class BuilderPatternGenerator {

    static String templateName = "builderPattern.jt";

    public static void makeBuilderOf(Class<?> clazz, String projectRootPath) throws IOException {
        ContextMap context = ContextMap.newContext();
        String builderName = clazz.getSimpleName() + "Builder";
        Set<FieldContext> fieldContextList = FieldUtil.getFields(clazz).stream().map(FieldContext::fromField).collect(Collectors.toSet());
        context.put("package", clazz.getPackage().getName());
        context.put("original_class", clazz.getSimpleName());
        context.put("class_name", builderName);
        context.put("fields", fieldContextList);
        String clazzData = JinjaTemplate.render(templateName, context);
        ClassWriterUtils.writeClass(builderName, clazzData,
                projectRootPath, clazz.getPackage().getName());
    }

}
