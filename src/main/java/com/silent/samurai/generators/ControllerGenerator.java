package com.silent.samurai.generators;

import com.silent.samurai.JavaGenerator;
import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FileList;
import com.silent.samurai.templates.JinjaTemplate;
import com.silent.samurai.utils.ClassWriterUtils;
import com.silent.samurai.utils.Inflector;

import java.io.IOException;

public class ControllerGenerator {

    static String template = "controller.jt";

    public static FileList make(Class<?> entity, JavaGenerator generator) throws IOException {
        ContextMap context = ContextMap.newContext();
        String entityName = entity.getSimpleName();

        String entityInstance = Inflector.getInstance().classToInstance(entityName);
        String entityInstancePlural = Inflector.getInstance().pluralize(entityInstance);
        String basePackageName = generator.getPackageName();
        String packageName = basePackageName + ".controllers";

        String className = entity.getSimpleName() + "Controller";

//        String idClassName = idClass.getSimpleName();

        String apiFragment = Inflector.getInstance().classToUriFragment(entityName);

        String responseClass = "Light" + entityName + "Response";

        String requestClass = entityName + "Request";


//        Set<Field> fields = FieldUtil.getFields(entity);
//        fields = fields.stream().filter(item -> !item.getName().equals("id")).collect(Collectors.toSet());
//
//        Table table = DatabaseUtil.getInstance().getTable(tableName);
//        List<Column> columns = table.getColumns();
//        Set<FieldContext> fieldContexts = FieldUtil.updateFieldsWithColumns(fields, columns, false);


        context.put("BASE_PACKAGE", basePackageName);
        context.put("ENTITY_CLASS_NAME", entityName);
        context.put("CLASS_NAME", className);
        context.put("API_FRAGMENT", apiFragment);
        context.put("ENTITY_INSTANCE", entityInstance);
        context.put("ENTITY_INSTANCE_PLURAL", entityInstancePlural);
        context.put("RESPONSE_CLASS", responseClass);
        context.put("REQUEST_CLASS", requestClass);

        String clazzData = JinjaTemplate.render(template, context);

        String absPath = ClassWriterUtils.writeClass(className, clazzData, generator.getProjectRoot(), packageName);

        return FileList.from(absPath);
    }

}
