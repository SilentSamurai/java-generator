package com.silent.samurai.helper;

import com.silent.samurai.utils.CommonUtil;

import java.lang.reflect.Field;

public class FieldContext {

    public String name;
    public String type;
    public String modifier;


    public static FieldContext fromField(Field field) {
        FieldContext fieldContext = new FieldContext();
        fieldContext.name = field.getName();
        fieldContext.type = field.getType().getSimpleName();
        fieldContext.modifier = CommonUtil.toModifierString(field.getModifiers());
        return fieldContext;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getModifier() {
        return modifier;
    }
}
