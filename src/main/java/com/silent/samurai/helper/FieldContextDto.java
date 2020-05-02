package com.silent.samurai.helper;


public class FieldContextDto {
    private String modifier;
    private Boolean isUnique;
    private String type;
    private Boolean isIndexed;
    private String name;
    private Boolean isNullable;
    private Boolean isGenerated;
    private Boolean isAutoInc;

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsIndexed() {
        return isIndexed;
    }

    public void setIsIndexed(Boolean isIndexed) {
        this.isIndexed = isIndexed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }

    public Boolean getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(Boolean isGenerated) {
        this.isGenerated = isGenerated;
    }

    public Boolean getIsAutoInc() {
        return isAutoInc;
    }

    public void setIsAutoInc(Boolean isAutoInc) {
        this.isAutoInc = isAutoInc;
    }


}