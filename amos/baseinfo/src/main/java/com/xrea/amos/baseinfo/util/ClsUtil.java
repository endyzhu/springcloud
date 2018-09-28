package com.xrea.amos.baseinfo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体类操作类
 *
 * @author zhuqb
 */
public class ClsUtil {
    /**
     * 获取类以及其所有父类的属性
     *
     * @param cls
     * @return
     */
    public static List<Field> getAndSuperField(Class cls) {
        List<Field> list = new ArrayList<Field>();
        Class ctype = cls;
        Field[] fields = null;
        while (null != ctype) {
            fields = ctype.getDeclaredFields();
            for (Field field : fields) {
                list.add(field);
            }
            ctype = ctype.getSuperclass();
        }
        return list;
    }
}
