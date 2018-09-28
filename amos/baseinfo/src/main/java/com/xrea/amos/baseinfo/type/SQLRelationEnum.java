package com.xrea.amos.baseinfo.type;

public enum SQLRelationEnum {
    AND(1, "AND"),
    OR(2, "OR"),
    NOT(3, "NOT");

    private int type;
    private String relation;

    private SQLRelationEnum(int type, String relation) {
        this.type = type;
        this.relation = relation;
    }

    public int getType() {
        return this.type;
    }

    public String getRelation() {
        return this.relation;
    }

    public String getRelationWithSpace() {
        return " " + this.relation + " ";
    }

    public String getRelationByRegex() {
        String regexStr = null;
        if (AND != this && OR != this) {
            if (NOT == this) {
                regexStr = "(^" + this.getRelation() + "(" + "\\s+" + "|" + "\\s{0,}" + "\\(" + "))";
            }
        } else {
            regexStr = "(\\s+" + this.getRelation() + "\\s+" + ")";
        }

        return regexStr;
    }
}
