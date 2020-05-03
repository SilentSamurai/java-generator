package com.silent.samurai.generators;

import com.google.common.base.CaseFormat;
import com.silent.samurai.JavaGenerator;
import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FieldContext;
import com.silent.samurai.helper.FileList;
import com.silent.samurai.templates.JinjaTemplate;
import com.silent.samurai.utils.ClassWriterUtils;
import com.silent.samurai.utils.FieldUtil;

import java.io.IOException;
import java.util.Set;

public class HttpClientGenerator {


    public static FileList make(Class<?> entity, String pathFromRoot, JavaGenerator generator) throws IOException {
        ContextMap context = ContextMap.newContext();
        String entityName = entity.getSimpleName();

        String entityApi = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, entityName);
        entityName = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, entityName);

        Set<FieldContext> fieldContexts = FieldUtil.getFieldContexts(entity);

        context.put("ENTITY_API", entityApi);
        context.put("FIELDS", fieldContexts);
        context.put("ENTITY_NAME", entityName);
        context.put("ENTITY_ID_VAR", entityName + "_ID");

        String clazzData = JinjaTemplate.render("entity-http.jt", context);

        String absPath = ClassWriterUtils.writeFile(pathFromRoot + "/" + entityApi + ".http", clazzData, generator.getProjectRoot());

        return FileList.from(absPath);
    }

}
