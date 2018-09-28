package com.xrea.amos.baseinfo.entity;

import com.xrea.amos.baseinfo.type.SQLFunctionEnum;
import com.xrea.amos.baseinfo.type.SQLOperatorEnum;
import com.xrea.amos.baseinfo.type.SQLRelationEnum;
import com.xrea.amos.baseinfo.type.SQLSortEnum;

import java.util.*;

/**
 * 条件封装
 * @author zhuqb
 */
public class OrmParam {

    protected boolean isLogicDelete = false;
    protected boolean isDistinct = false;
    protected List<String> columns = new ArrayList();
    protected Map<String, Object> columnValueMapping = new HashMap();
    protected String whereExp = null;
    protected Map<String, Object> whereParams = new HashMap();
    protected Integer pageSize = null;
    protected Integer pageNo = null;
    protected Set<String> groupColumnNames = new HashSet();
    protected String havingExp = null;
    protected String orderExp = null;

    public OrmParam() {
    }

    public void reset() {
        this.isLogicDelete = false;
        this.isDistinct = false;
        this.columns.clear();
        this.columnValueMapping.clear();
        this.whereParams.clear();
        this.groupColumnNames.clear();
        this.whereExp = null;
        this.havingExp = null;
        this.orderExp = null;
        this.pageSize = null;
        this.pageNo = null;
    }

    /** @deprecated */
    @Deprecated
    public void clearOrmParmas() {
        this.reset();
    }

    /** @deprecated */
    @Deprecated
    public OrmParam addWhereParam(String key, Object value) {
        this.whereParams.put(key, value);
        return this;
    }

    public OrmParam addColumn(String column) {
        this.columns.add(column);
        return this;
    }

    public List<String> getColumns() {
        return this.columns;
    }

    public OrmParam setColumns(List<String> columns) {
        if (columns == null) {
            columns.clear();
        } else {
            this.columns = columns;
        }

        return this;
    }

    public Map<String, Object> getColumnValueMapping() {
        return this.columnValueMapping;
    }

    public void setColumnValueMapping(Map<String, Object> columnValueMapping) {
        if (columnValueMapping == null) {
            columnValueMapping.clear();
        } else {
            this.columnValueMapping = columnValueMapping;
        }

    }

    public String getWhereExp() {
        return this.whereExp;
    }

    public OrmParam setWhereExp(String whereExp) {
        whereExp = "".equals(whereExp) ? null : whereExp;
        this.whereExp = whereExp;
        return this;
    }

    public Map<String, Object> getWhereParams() {
        return this.whereParams;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public OrmParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public OrmParam setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    protected static final boolean isAggregateFunctionColumn(String fieldName) {
        return -1 != fieldName.indexOf("(") || -1 != fieldName.indexOf(")");
    }

    public Set<String> getGroupByColumns() {
        return this.groupColumnNames;
    }

    public OrmParam addGroupByExp(Set<String> groupByColumns) {
        this.addGroupByColumn((String[])groupByColumns.toArray(new String[groupByColumns.size()]));
        return this;
    }

    public OrmParam addGroupByColumn(String... fieldNames) {
        String[] var2 = fieldNames;
        int var3 = fieldNames.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String fieldName = var2[var4];
            if (!isAggregateFunctionColumn(fieldName) && 0 <= this.columns.indexOf(fieldName)) {
                this.groupColumnNames.add(fieldName);
            }
        }

        return this;
    }

    public OrmParam setGroupByColumns(Set<String> groupColumnNames) {
        this.groupColumnNames.clear();
        Iterator var2 = groupColumnNames.iterator();

        while(var2.hasNext()) {
            String fieldName = (String)var2.next();
            if (0 <= this.columns.indexOf(fieldName)) {
                this.groupColumnNames.add(fieldName);
            }
        }

        return this;
    }

    public String getHavingExp() {
        return this.havingExp;
    }

    public OrmParam setHavingExp(String havingExp) {
        havingExp = "".equals(havingExp) ? null : havingExp;
        this.havingExp = havingExp;
        return this;
    }

    public String getOrderExp() {
        return this.orderExp;
    }

    public OrmParam addOrderExpElement(SQLSortEnum sqlSortEnum, String sortColumn) {
        if (this.orderExp != null && !"".equals(this.orderExp)) {
            this.orderExp = this.orderExp + "," + sortColumn + sqlSortEnum.getSortTypeWithSpace();
        } else {
            this.orderExp = sortColumn + sqlSortEnum.getSortTypeWithSpace();
        }

        return this;
    }

    public OrmParam setOrderExp(SQLSortEnum sqlSortEnum, String sortColumn1, String... sortColumns) {
        StringBuffer sortColumnExp = new StringBuffer();
        sortColumnExp.append(sortColumn1).append(sqlSortEnum.getSortTypeWithSpace());

        for(int i = 0; i < sortColumns.length; ++i) {
            sortColumnExp.append(",").append(sortColumns[i]).append(sqlSortEnum.getSortTypeWithSpace());
        }

        this.orderExp = sortColumnExp.toString();
        return this;
    }

    public OrmParam setOrderExp(SQLSortEnum sqlSortEnum, List<String> orderList) {
        if (0 == orderList.size()) {
            return this;
        } else {
            String sortColumn1 = (String)orderList.remove(0);
            String[] orderArray = new String[orderList.size()];
            orderList.toArray(orderArray);
            this.setOrderExp(sqlSortEnum, sortColumn1, orderArray);
            return this;
        }
    }

    protected static final String formatValueXML(String mapKey, boolean isNeedPlaceholder) {
        return isNeedPlaceholder ? "#{whereParam." + mapKey + "}" : "${whereParam." + mapKey + "}";
    }

    public static final String formatCondtionXML(String fieldName, SQLOperatorEnum sqlOperatorEnum, String mapKey) {
        return fieldName + sqlOperatorEnum.getOperatorWithSpace() + formatValueXML(mapKey, true);
    }

    protected String createDistinctMapKey() {
        return String.valueOf(this.whereParams.size());
    }

    protected String createDistinctMapKey(String fieldName) {
        return fieldName + String.valueOf(this.whereParams.size());
    }

    protected String putParamMapAndBackValueXML(String fieldName, Object fieldValue) {
        String mapKey = this.createDistinctMapKey(fieldName);
        this.whereParams.put(mapKey, fieldValue);
        return formatValueXML(mapKey, true);
    }

    public boolean isInvalid() {
        return this.isLogicDelete;
    }

    public OrmParam isInvalidCondition(boolean isInvalid) {
        this.isLogicDelete = isInvalid;
        return this;
    }

    public boolean isDistinct() {
        return this.isDistinct;
    }

    public OrmParam setDistinct(boolean distinct) {
        this.isDistinct = distinct;
        return this;
    }

    public String getIsNull(String fieldName) {
        return fieldName + SQLOperatorEnum.ISNULL.getOperatorWithSpace();
    }

    public String getIsNotNull(String fieldName) {
        return fieldName + SQLOperatorEnum.ISNOTNULL.getOperatorWithSpace();
    }

    protected String setBinaryOperator(String fieldName, SQLOperatorEnum sqlOperatorEnum, Object fieldValue) {
        return fieldName + sqlOperatorEnum.getOperatorWithSpace() + this.putParamMapAndBackValueXML(fieldName, fieldValue);
    }

    public String getEqualXML(String fieldName, Object fieldValue) {
        return null == fieldValue ? null : this.setBinaryOperator(fieldName, SQLOperatorEnum.EQ, fieldValue);
    }

    public String getUnequalXML(String fieldName, Object fieldValue) {
        return null == fieldValue ? null : this.setBinaryOperator(fieldName, SQLOperatorEnum.UNEQ, fieldValue);
    }

    public String getLessThanXML(String fieldName, Object fieldValue) {
        return null == fieldValue ? null : this.setBinaryOperator(fieldName, SQLOperatorEnum.LT, fieldValue);
    }

    public String getLessThanAndEqualXML(String fieldName, Object fieldValue) {
        return null == fieldValue ? null : this.setBinaryOperator(fieldName, SQLOperatorEnum.LTE, fieldValue);
    }

    public String getGreaterThanXML(String fieldName, Object fieldValue) {
        return null == fieldValue ? null : this.setBinaryOperator(fieldName, SQLOperatorEnum.GT, fieldValue);
    }

    public String getGreaterThanAndEqualXML(String fieldName, Object fieldValue) {
        return null == fieldValue ? null : this.setBinaryOperator(fieldName, SQLOperatorEnum.GTE, fieldValue);
    }

    protected String formatLikeValue(String likeString) {
        return likeString.replaceAll("\\%", "\\\\%");
    }

    public String getMatchMiddleXML(String fieldName, String fieldValue) {
        if (null != fieldValue && !"".equals(fieldValue)) {
            String valueMatch = "%" + this.formatLikeValue(fieldValue) + "%";
            return this.setBinaryOperator(fieldName, SQLOperatorEnum.LIKE, valueMatch);
        } else {
            return null;
        }
    }


    public String getMatchLeftXML(String fieldName, String fieldValue) {
        if (null != fieldValue && !"".equals(fieldValue)) {
            String valueMatch = this.formatLikeValue(fieldValue) + "%";
            return this.setBinaryOperator(fieldName, SQLOperatorEnum.LIKE, valueMatch);
        } else {
            return null;
        }
    }

    public String getMathRightXML(String fieldName, String fieldValue) {
        if (null != fieldValue && !"".equals(fieldValue)) {
            String valueMatch = "%" + this.formatLikeValue(fieldValue);
            return this.setBinaryOperator(fieldName, SQLOperatorEnum.LIKE, valueMatch);
        } else {
            return null;
        }
    }

    public String getUnlikeXML(String fieldName, String fieldValue) {
        if (null != fieldValue && !"".equals(fieldValue)) {
            String valueMatch = "%" + this.formatLikeValue(fieldValue) + "%";
            return this.setBinaryOperator(fieldName, SQLOperatorEnum.UNLIKE, valueMatch);
        } else {
            return null;
        }
    }

    public String getLeftUnlikeXML(String fieldName, String fieldValue) {
        if (null != fieldValue && !"".equals(fieldValue)) {
            String valueMatch = this.formatLikeValue(fieldValue) + "%";
            return this.setBinaryOperator(fieldName, SQLOperatorEnum.UNLIKE, valueMatch);
        } else {
            return null;
        }
    }

    public String getRightUnlikeXML(String fieldName, String fieldValue) {
        if (null != fieldValue && !"".equals(fieldValue)) {
            String valueMatch = "%" + this.formatLikeValue(fieldValue);
            return this.setBinaryOperator(fieldName, SQLOperatorEnum.UNLIKE, valueMatch);
        } else {
            return null;
        }
    }

    protected String paramasToBetweenXML(String fieldName, Object fieldValue1, Object fieldValue2) {
        String valueXml1 = this.putParamMapAndBackValueXML(fieldName, fieldValue1);
        String valueXml2 = this.putParamMapAndBackValueXML(fieldName, fieldValue2);
        return valueXml1 + SQLRelationEnum.AND.getRelationWithSpace() + valueXml2;
    }

    public String getBetweenXML(String fieldName, Object fieldValue1, Object fieldValue2) {
        return null != fieldValue1 && null != fieldValue2 ? fieldName + SQLOperatorEnum.BETWEEN.getOperatorWithSpace() + this.paramasToBetweenXML(fieldName, fieldValue1, fieldValue2) : null;
    }

    public String getNotBetweenXML(String fieldName, Object fieldValue1, Object fieldValue2) {
        return null != fieldValue1 && null != fieldValue2 ? fieldName + SQLOperatorEnum.NOTBETWEEN.getOperatorWithSpace() + this.paramasToBetweenXML(fieldName, fieldValue1, fieldValue2) : null;
    }

    protected static final Object[] invalidDataFilterForIn(Object... objects) {
        Set<Object> list = new HashSet(Arrays.asList(objects));
        list.removeAll(Collections.singleton((Object)null));
        return list.toArray();
    }

    protected String paramsSpiltByCommaXML(String fieldName, Object[] fieldValues) {
        StringBuilder inValuesSetStringBuilder = new StringBuilder("(");
        int len = fieldValues.length;

        for(int i = 0; i < len; ++i) {
            String xmlValue = this.putParamMapAndBackValueXML(fieldName, fieldValues[i]);
            inValuesSetStringBuilder.append(",").append(xmlValue);
        }

        String inValuesSetString = inValuesSetStringBuilder.toString().replaceFirst(",", "");
        inValuesSetString = inValuesSetString + ")";
        return inValuesSetString;
    }

    /** @deprecated */
    @Deprecated
    public String getConditionForInXML(String fieldName, Object[] objects) {
        return this.getInXML(fieldName, objects);
    }

    /** @deprecated */
    @Deprecated
    public String getConditionForNotInXML(String fieldName, Object[] objects) {
        return this.getNotInXML(fieldName, objects);
    }

    public String getInXML(String fieldName, Object[] objects) {
        objects = invalidDataFilterForIn(objects);
        return objects.length == 0 ? null : fieldName + SQLOperatorEnum.IN.getOperatorWithSpace() + this.paramsSpiltByCommaXML(fieldName, objects);
    }

    public String getNotInXML(String fieldName, Object[] objects) {
        objects = invalidDataFilterForIn(objects);
        return objects.length == 0 ? null : fieldName + SQLOperatorEnum.NOTIN.getOperatorWithSpace() + this.paramsSpiltByCommaXML(fieldName, objects);
    }

    protected static final String[] invalidStringFilter(String... array) {
        List<String> list = new ArrayList();
        String[] validStrings = array;
        int var3 = array.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String str = validStrings[var4];
            if (null != str && !"".equals(str)) {
                list.add(str);
            }
        }

        validStrings = new String[list.size()];
        return (String[])list.toArray(validStrings);
    }

    protected static final String appendRelation(SQLRelationEnum sqlRelationEnum, String... conditionXmls) {
        int len = conditionXmls.length;
        boolean isMutipule = 1 < len;
        StringBuilder stringBuilder = (new StringBuilder(isMutipule ? "(" : "")).append(conditionXmls[0]);

        for(int i = 1; i < len; ++i) {
            stringBuilder.append(sqlRelationEnum.getRelationWithSpace()).append(conditionXmls[i]);
        }

        stringBuilder.append(isMutipule ? ")" : "");
        return stringBuilder.toString();
    }

    public static final String and(List<String> conditionList) {
        if (null == conditionList) {
            return null;
        } else {
            String[] conditions = new String[conditionList.size()];
            return and((String[])conditionList.toArray(conditions));
        }
    }

    public static final String and(String... conditionXmls) {
        String[] xmls = invalidStringFilter(conditionXmls);
        int len = xmls.length;
        return len == 0 ? null : appendRelation(SQLRelationEnum.AND, xmls);
    }

    public static final String or(List<String> conditionList) {
        if (null == conditionList) {
            return null;
        } else {
            String[] conditions = new String[conditionList.size()];
            return or((String[])conditionList.toArray(conditions));
        }
    }

    public static final String or(String... conditionXmls) {
        String[] xmls = invalidStringFilter(conditionXmls);
        int len = xmls.length;
        return len == 0 ? null : appendRelation(SQLRelationEnum.OR, xmls);
    }

    public static final String not(String conditionXml) {
        if (null != conditionXml && !"".equals(conditionXml)) {
            StringBuilder notStringBuilder = (new StringBuilder(SQLRelationEnum.NOT.getRelationWithSpace())).append("(").append(conditionXml).append(")");
            return notStringBuilder.toString();
        } else {
            return null;
        }
    }

    protected static final String getFunctionXML(SQLFunctionEnum sqlFunctionEnum, String columnName) {
        return sqlFunctionEnum.getFunctionName() + "(" + columnName + ")";
    }

    public static final String sqlAvgFunctionXML(String columnName) {
        return getFunctionXML(SQLFunctionEnum.AVG, columnName);
    }

    public static final String sqlCountFunctionXML(String columnName) {
        return getFunctionXML(SQLFunctionEnum.COUNT, columnName);
    }

    public static final String sqlMaxFunctionXML(String columnName) {
        return getFunctionXML(SQLFunctionEnum.MAX, columnName);
    }

    public static final String sqlMinFunctionXML(String columnName) {
        return getFunctionXML(SQLFunctionEnum.MIN, columnName);
    }

    public static final String sqlSumFunctionXML(String columnName) {
        return getFunctionXML(SQLFunctionEnum.SUM, columnName);
    }

    public boolean isHasAggregateColumn() {
        Iterator var1 = this.columns.iterator();

        String column;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            column = (String)var1.next();
        } while(0 > column.indexOf("(") && 0 > column.indexOf(")"));

        return true;
    }
}
