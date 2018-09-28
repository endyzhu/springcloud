package com.xrea.amos.baseinfo.type;

public enum SQLOperatorEnum {
    EQ(0, "=", "等于"),
    UNEQ(1, "<>", "不等于"),
    LT(2, "<", "小于"),
    LTE(3, "<=", "小于等于"),
    GT(4, ">", "大于"),
    GTE(5, ">=", "大于等于"),
    LIKE(6, "LIKE", "like"),
    UNLIKE(7, "NOT LIKE", "not like"),
    BETWEEN(30, "BETWEEN", "between"),
    NOTBETWEEN(31, "NOT BETWEEN", "not between"),
    IN(32, "IN", "in"),
    NOTIN(33, "NOT IN", "not in"),
    ISNULL(100, "IS NULL", "is null"),
    ISNOTNULL(101, "IS NOT NULL", "is not null");

    private int sequence;
    private String operator;
    private String desc;

    private SQLOperatorEnum(int sequence, String operator, String desc) {
        this.sequence = sequence;
        this.operator = operator;
        this.desc = desc;
    }

    public int getSequence() {
        return this.sequence;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getOperatorWithSpace() {
        return " " + this.operator + " ";
    }

    public String getOperatorRegex() {
        String opRegex = "";
        if (!EQ.equals(this) && !UNEQ.equals(this) && !LT.equals(this) && !LTE.equals(this) && !GT.equals(this) && !GTE.equals(this)) {
            if (IN.equals(this)) {
                opRegex = "\\s+" + this.getOperator() + "\\s+";
            } else if (NOTIN.equals(this)) {
                opRegex = "\\s+NOT\\s+IN\\s+";
            } else if (ISNULL.equals(this)) {
                opRegex = "\\s+IS\\s+NULL\\s+";
            } else if (ISNOTNULL.equals(this)) {
                opRegex = "\\s+IS\\s+NOT\\s+NULL\\s+";
            } else if (LIKE.equals(this)) {
                opRegex = "\\s+LIKE\\s+";
            } else if (UNLIKE.equals(this)) {
                opRegex = "\\s+NOT\\s+LIKE\\s+";
            } else if (BETWEEN.equals(this)) {
                opRegex = "\\s+" + this.getOperator() + "\\s+";
            } else if (NOTBETWEEN.equals(this)) {
                opRegex = "\\s+NOT\\s+BETWEEN\\s+";
            } else {
                opRegex = "\\s+" + this.getOperator() + "\\s+";
            }
        } else {
            opRegex = "\\s{0,}" + this.getOperator() + "\\s{0,}";
        }

        return opRegex;
    }
}
