package com.silent.samurai.generators;

import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FieldContext;
import com.silent.samurai.templates.JinjaTemplate;
import com.silent.samurai.utils.ClassWriterUtils;
import com.silent.samurai.utils.FieldUtil;
import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateDtoClass {

    static String template = "dto.jt";


    public static List<String> make(Class<?> entity, String suffix, Boolean applyValidation, String projectRoot) throws IOException {
        ContextMap context = ContextMap.newContext();
        String className = entity.getSimpleName() + WordUtils.capitalize(suffix);
        String packageName = entity.getPackage().getName();

        Set<FieldContext> fieldContextList = FieldUtil.getFields(entity).stream()
                .map(FieldContext::fromField).collect(Collectors.toSet());

        context.put("CLASS_NAME", className);
        context.put("FIELDS", fieldContextList);
        context.put("PACKAGE", packageName);

        String clazzData = JinjaTemplate.render(template, context);

        String absPath = ClassWriterUtils.writeClass(className, clazzData, projectRoot, packageName);

        return Collections.singletonList(absPath);
    }

}
