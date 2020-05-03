package com.silent.samurai.utils;

import com.silent.samurai.helper.ContextMap;
import com.silent.samurai.helper.FieldContext;
import org.apache.commons.text.CaseUtils;
import org.apache.log4j.Logger;
import org.reflections.ReflectionUtils;
import schemacrawler.schema.Column;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FieldUtil {

    static final Logger logger = Logger.getLogger(FieldUtil.class);

    public static Set<FieldContext> updateFieldsWithColumns(Set<Field> fields, List<Column> columns, Boolean addExtraFields) {
        ContextMap contextMap = ContextMap.newContext();

        columns.forEach(item -> {
            String fieldName = CaseUtils.toCamelCase(item.getName(), false, '_');
            contextMap.put(fieldName, item);
        });

        Set<FieldContext> fieldContexts = new HashSet<>();

        for (Field field : fields) {
            Column column = (Column) contextMap.get(field.getName());
            if (!addExtraFields && column == null)
                continue;
            FieldContext fieldContext = FieldContext.fromField(field);
            if (column != null)
                fieldContext.update(column);
            fieldContexts.add(fieldContext);
        }
        return fieldContexts;
    }

    public static Set<FieldContext> updateFieldsWithColumns(Set<Field> fields, List<Column> columns) {
        return updateFieldsWithColumns(fields, columns, true);
    }

    public static Set<Field> getFields(Class<?> clazz) {
        return ReflectionUtils.getFields(clazz, CommonUtil::isInstanceField);
    }

    public static Set<FieldContext> getFieldContexts(Class<?> clazz) {
        return getFields(clazz).stream()
                .map(FieldContext::fromField).collect(Collectors.toSet());
    }


}
