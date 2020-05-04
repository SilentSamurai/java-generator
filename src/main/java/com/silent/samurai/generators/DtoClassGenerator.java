package com.silent.samurai.generators;

import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FieldContext;
import com.silent.samurai.templates.JinjaTemplate;
import com.silent.samurai.utils.ClassWriterUtils;
import com.silent.samurai.utils.DatabaseUtil;
import com.silent.samurai.utils.FieldUtil;
import org.apache.commons.text.WordUtils;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DtoClassGenerator {

    static String template = "dto.jt";
    static String templateValidation = "dto-validation.jt";


    public static List<String> make(Class<?> entity, String suffix, Boolean applyValidation, String tableName, String projectRoot) throws IOException {
        ContextMap context = ContextMap.newContext();
        String className = entity.getSimpleName() + WordUtils.capitalize(suffix);
        String packageName = entity.getPackage().getName();

        Set<Field> fields = FieldUtil.getFields(entity);

        Set<FieldContext> fieldContexts;

        if (applyValidation) {
            Table table = DatabaseUtil.getInstance().getTable(tableName);
            List<Column> columns = table.getColumns();
            fieldContexts = FieldUtil.updateFieldsWithColumns(fields, columns);
        } else {
            fieldContexts = FieldUtil.getFieldContexts(entity);
        }

        context.put("CLASS_NAME", className);
        context.put("FIELDS", fieldContexts);
        context.put("PACKAGE", packageName);

        String clazzData = JinjaTemplate.render(applyValidation ? templateValidation : template, context);

        String absPath = ClassWriterUtils.writeClass(className, clazzData, projectRoot, packageName);

        return Collections.singletonList(absPath);
    }

}
