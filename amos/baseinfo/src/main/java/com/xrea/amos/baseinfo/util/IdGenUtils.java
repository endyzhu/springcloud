package com.xrea.amos.baseinfo.util;

import java.util.UUID;

/**
 * UUID 操作类
 * @author zhuqb
 */
public class IdGenUtils {
    /**
     * 生成随机UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
