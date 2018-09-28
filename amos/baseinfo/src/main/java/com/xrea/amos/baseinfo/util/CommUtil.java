package com.xrea.amos.baseinfo.util;

import com.xrea.amos.baseinfo.base.SystemConst;

import java.util.Collection;
import java.util.Map;

/**
 * 通用操作类
 * @author zhuqb
 */
public class CommUtil {
    /**
     * 判断对象是否为空
     * 适用于对如下对象做判断:String Collection及其子类 Map及其子类
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj){
        if (obj == null) {
            return true;
        }
        if (obj == "") {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).size() == 0;
        }
        return false;
    }

    /**
     * 判断对象不为空
     * 适用于String Collection及其子类 Map及其子类
     * @param obj
     * @return
     */
    public static boolean isNotNullOrEmpty(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj == "") {
            return false;
        }
        if (obj instanceof String) {
            return ((String) obj).length() != 0;
        } else if (obj instanceof Collection) {
            return !((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).size() != 0;
        }
        return true;
    }

    /**
     * 驼峰转下划线
     * @param camelStr
     * @return
     */
    public static String camelToUnderline(String camelStr){
        if (isNullOrEmpty(camelStr)) {
            return "";
        }
        int len = camelStr.length();
        StringBuffer sb = new StringBuffer(len);
        for (int i=0; i<len; i++){
            char c = camelStr.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(SystemConst.UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param underlineStr
     * @return
     */
    public static String underlineToCamel(String underlineStr){
        if (isNullOrEmpty(underlineStr)){
            return "";
        }
        int len = underlineStr.length();
        StringBuffer sb = new StringBuffer(len);
        for (int i=0; i<len; i++){
            char c = underlineStr.charAt(i);
            if (c == SystemConst.UNDERLINE){
               if (++i < len) {
                   sb.append(Character.toUpperCase(underlineStr.charAt(i)));
               }
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 把一个字符串的第一个字母大写、效率是最高的、
     * @param fildeName
     * @return
     * @throws Exception
     */
    public static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
