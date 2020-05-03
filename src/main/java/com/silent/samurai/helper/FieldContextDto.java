package com.silent.samurai.helper;



public class FieldContextDto {
    private Boolean isGenerated;
    private Boolean isNullable;
    private Boolean isUnique;
    private String name;
    private Boolean isAutoInc;
    private String modifier;
    private Boolean isIndexed;
    private String type;

    public Boolean getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(Boolean isGenerated) {
        this.isGenerated = isGenerated;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAutoInc() {
        return isAutoInc;
    }

    public void setIsAutoInc(Boolean isAutoInc) {
        this.isAutoInc = isAutoInc;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Boolean getIsIndexed() {
        return isIndexed;
    }

    public void setIsIndexed(Boolean isIndexed) {
        this.isIndexed = isIndexed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}