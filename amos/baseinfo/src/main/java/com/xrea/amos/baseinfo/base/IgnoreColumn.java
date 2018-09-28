package com.xrea.amos.baseinfo.base;

import java.lang.annotation.*;

/**
 * 操作数据库时忽略该字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreColumn {
}
