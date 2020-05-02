package com.silent.samurai.helper;

import com.silent.samurai.utils.CommonUtil;
import schemacrawler.schema.Column;

import java.lang.reflect.Field;

public class FieldContext {

    public String name;
    public String type;
    public String modifier;
    public Boolean isUnique;
    public Boolean isNullable;
    public Boolean isIndexed;
    public Boolean isGenerated;
    public Boolean isAutoInc;


    public static FieldContext fromField(Field field) {
        FieldContext fieldContext = new FieldContext();
        fieldContext.name = field.getName();
        fieldContext.type = field.getType().getSimpleName();
        fieldContext.modifier = CommonUtil.toModifierString(field.getModifiers());
        return fieldContext;
    }

    public void update(Column column) {
        this.isNullable = column.isNullable();
        this.isUnique = column.isPartOfUniqueIndex();
        this.isIndexed = column.isPartOfIndex();
        this.isGenerated = column.isGenerated();
        this.isAutoInc = column.isAutoIncremented();
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

    public Boolean getUnique() {
        return isUnique;
    }

    public Boolean getNullable() {
        return isNullable;
    }

    public Boolean getIndexed() {
        return isIndexed;
    }

    public Boolean getGenerated() {
        return isGenerated;
    }

    public Boolean getAutoInc() {
        return isAutoInc;
    }
}
