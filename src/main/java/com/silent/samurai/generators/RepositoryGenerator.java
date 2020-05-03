package com.silent.samurai.generators;

import com.silent.samurai.JavaGenerator;
import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FieldContext;
import com.silent.samurai.helper.FileList;
import com.silent.samurai.templates.JinjaTemplate;
import com.silent.samurai.utils.ClassWriterUtils;
import com.silent.samurai.utils.DatabaseUtil;
import com.silent.samurai.utils.FieldUtil;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class RepositoryGenerator {

    static String template = "repository.jt";

    public static FileList make(Class<?> entity, Class<?> idClass, String tableName, JavaGenerator generator) throws IOException {
        ContextMap context = ContextMap.newContext();
        String entityName = entity.getSimpleName();
        String basePackageName = generator.getPackageName();
        String packageName = basePackageName + ".repository";

        String className = entity.getSimpleName() + "Repository";

        String idClassName = idClass.getSimpleName();

        Set<Field> fields = FieldUtil.getFields(entity);
        Table table = DatabaseUtil.getInstance().getTable(tableName);
        List<Column> columns = table.getColumns();
        Set<FieldContext> fieldContexts = FieldUtil.updateFieldsWithColumns(fields, columns);


        context.put("BASE_PACKAGE", basePackageName);
        context.put("ENTITY_CLASS_NAME", entityName);
        context.put("CLASS_NAME", className);
        context.put("FIELDS", fieldContexts);
        context.put("ID_CLASS", idClassName);

        String clazzData = JinjaTemplate.render(template, context);

        String absPath = ClassWriterUtils.writeClass(className, clazzData, generator.getProjectRoot(), packageName);

        return FileList.from(absPath);
    }
}
