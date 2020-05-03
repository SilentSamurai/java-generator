package com.silent.samurai.helper;

import com.silent.samurai.utils.CommonUtil;
import schemacrawler.schema.Column;

import java.lang.reflect.Field;

public class FieldContext {

    public String name;
    public String type;
    public String modifier;
    public Boolean _isUnique = false;
    public Boolean _isNullable = true;
    public Boolean _isIndexed = false;
    public Boolean _isGenerated = false;
    public Boolean _isAutoInc = false;


    public static FieldContext fromField(Field field) {
        FieldContext fieldContext = new FieldContext();
        fieldContext.name = field.getName();
        fieldContext.type = field.getType().getSimpleName();
        fieldContext.modifier = CommonUtil.toModifierString(field.getModifiers());
        return fieldContext;
    }

    public void update(Column column) {
        this._isNullable = column.isNullable();
        this._isUnique = column.isPartOfUniqueIndex();
        this._isIndexed = column.isPartOfIndex();
        this._isGenerated = column.isGenerated();
        this._isAutoInc = column.isAutoIncremented();
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

    public Boolean isUnique() {
        return _isUnique;
    }

    public Boolean isNullable() {
        return _isNullable;
    }

    public Boolean isIndexed() {
        return _isIndexed;
    }

    public Boolean isGenerated() {
        return _isGenerated;
    }

    public Boolean getAutoInc() {
        return _isAutoInc;
    }
}
