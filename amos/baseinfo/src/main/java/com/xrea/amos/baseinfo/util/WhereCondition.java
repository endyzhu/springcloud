package com.xrea.amos.baseinfo.util;

import com.xrea.amos.baseinfo.base.SystemConst;
import com.xrea.amos.baseinfo.type.SQLOperatorEnum;
import com.xrea.amos.baseinfo.type.SQLRelationEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 条件封装类
 * @author zhuqb
  */
public class WhereCondition {
    /**
     * 条件字符串
     */
    private String whereExp = null;
    /**
     * 条件集合
     */
    private List<String> whereCondition = new ArrayList<String>();


    /**
     * 查询条件 where 表达式中的参数集
     */
    protected Map<String, Object> whereParams;

    /**
     * 重置条件
     */
    public void reset(){
        this.whereExp = null;
        this.whereCondition.clear();
    }

    public  Map<String, Object> getParams(){
        return this.whereParams;
    }
    /**
     * 组装 = 的条件
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public WhereCondition getEqualCondtion(String fieldName,Object fieldValue){
        this.whereParams.put(fieldName,fieldValue);
        this.whereCondition.add(fieldName + SQLOperatorEnum.EQ.getOperatorWithSpace() + formatValueXML(fieldName,true));
        return this;
    }

    /**
     * 组装 <> 的条件
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public WhereCondition getUnEqualCondtion(String fieldName,Object fieldValue){
        this.whereParams.put(fieldName,fieldValue);
        this.whereCondition.add(fieldName + SQLOperatorEnum.UNEQ.getOperatorWithSpace() + formatValueXML(fieldName,true));
        return this;
    }

    /**
     * 左边模糊匹配
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public WhereCondition getLeftLikeCondition(String fieldName, Object fieldValue){
        this.whereParams.put(fieldName,fieldValue);
        this.whereCondition.add(fieldName + SQLOperatorEnum.LIKE.getOperatorWithSpace() + "%"+formatValueXML(fieldName,true) );
        return this;
    }

    /**
     * 右边边模糊匹配
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public WhereCondition getRightLikeCondition(String fieldName, Object fieldValue){
        this.whereParams.put(fieldName,fieldValue);
        this.whereCondition.add(fieldName + SQLOperatorEnum.LIKE.getOperatorWithSpace() + formatValueXML(fieldName,true) + "%" );
        return this;
    }

    /**
     * 中间模糊匹配
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public WhereCondition getMiddleLikeCondition(String fieldName, Object fieldValue){
        this.whereParams.put(fieldName,fieldValue);
        this.whereCondition.add(fieldName + SQLOperatorEnum.LIKE.getOperatorWithSpace() +  "%"+formatValueXML(fieldName,true) + "%" );
        return this;
    }

    /**
     * 条件为空
     * @param fieldName
     * @return
     */
    public WhereCondition getIsNullCondition(String fieldName){
        this.whereCondition.add(fieldName + SQLOperatorEnum.ISNULL.getOperatorWithSpace());
        return this;
    }

    /**
     * 条件不为空
     * @param fieldName
     * @return
     */
    public WhereCondition getIsNotNullCondition(String fieldName){
        this.whereCondition.add(fieldName + SQLOperatorEnum.ISNOTNULL.getOperatorWithSpace());
        return this;
    }

    /**
     * 构建条件参数
     * @return
     */
    public String build(){
        if (CommUtil.isNullOrEmpty(whereCondition)){
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        sb.append(" 1=1 ");
        for (String where : whereCondition){
            sb.append(SQLRelationEnum.AND.getRelation() + " " + where );
        }
        this.whereExp = sb.toString();
        return whereExp;
    }

    /**
     * 获取参数值得通用方法
     * @param mapKey
     * @param isNeedPlaceholder
     * @return
     */
    protected static final String formatValueXML(String mapKey, boolean isNeedPlaceholder) {
        if (isNeedPlaceholder) {
            //占位符号方式
            return "#{" + SystemConst.MyBatis.WHERE_PARAM + "." + mapKey + "}";
        } else {
            //非占位符方式
            return "${" + SystemConst.MyBatis.WHERE_PARAM  + "." + mapKey + "}";
        }
    }
}
