package com.xrea.amos.baseinfo.service;

import com.xrea.amos.baseinfo.entity.BaseEntity;
import com.xrea.amos.baseinfo.util.WhereCondition;

public interface BaseService<T> {
    <T extends BaseEntity> T save(T entity);

    <T extends BaseEntity> int deleteById(T entity);

    <T extends BaseEntity> int deleteByCondition(T entity, WhereCondition condition);

    <T extends BaseEntity> int updateByCondition(T entity,WhereCondition condition);
}
