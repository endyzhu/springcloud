package com.xrea.amos.baseinfo.type;

public enum SQLFunctionEnum {
    AVG(0, "AVG", "返回某列的平均值"),
    COUNT(1, "COUNT", "返回某列的行数（不包括 NULL 值)"),
    MAX(4, "MAX", "返回某列的最高值"),
    MIN(5, "MIN", "返回某列的最低值"),
    SUM(1, "SUM", "求和函数");

    private int type;
    private String functionName;
    private String desc;

    private SQLFunctionEnum(int type, String functionName, String desc) {
        this.type = type;
        this.functionName = functionName;
        this.desc = desc;
    }

    public int getType() {
        return this.type;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public String getDesc() {
        return this.desc;
    }
}
