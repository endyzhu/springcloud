package com.xrea.amos.baseinfo.base;

/**
 * 系统通用变量
 * @author zhuqb
 */
public class SystemConst {
    /**
     * 操作MyBatis通用的常量
     */
    public static final class MyBatis{
        public MyBatis(){}
        /**
         * 表名
         */
        public static final String TABLE_NAME = "TABLE_NAME";
        /**
         * 字段名
         */
        public static final String COLUMN_NAME = "COLUMN_NAME";
        /**
         * 字段值
         */
        public static final String COLUMN_VALUE = "COLUMN_VALUE";
        /**
         * 主键字段
         */
        public static final String KEY_ID = "KEY_ID";
        /**
         * 主键字段值
         */
        public static final String KEY_VALUE = "KEY_VALUE";

        public static final String WHERE_PARAM = "WHERE_PARAM";

        public static final String UPDATE_COLUM_VALUE = "UPDATE_COLUM_VALUE";

        public static final String ID = "ID";
    }

    /**
     * 是否删除枚举
     */
    public enum IsDelEnum{
        IS_DEL_NO(0,"是否删除-未删除"),

        IS_DEL_YES(1,"是否删除-是");

        IsDelEnum(int index,String desc){
            this.index = index;
            this.desc = desc;
        }
        private int index;
        private String desc;

        public int getIndex(){
            return index;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum StatusEnum{

        STATUS_ACTIVE(0,"激活"),
        STATUS_FROZEN(1,"冻结");

        StatusEnum(int index,String desc){
            this.index = index;
            this.desc = desc;
        }

        private int index;
        private String desc;

        public int getindex(){
            return index;
        }

        public String getDesc(){
            return desc;
        }

    }

    /**
     * 下划线
     */
    public static final char UNDERLINE = '_';
}
