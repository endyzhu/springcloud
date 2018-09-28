package com.xrea.amos.baseinfo.mapper;

import java.util.List;
import java.util.Map;

/**
 * 通用操作接口
 * @author zhuqb
 */
public interface BaseMapper{
    /**
     * 保存
     * @param entity
     * @return
     */
    int insert(Map<String,Object> entity);

    /**
     * 根据条件修改
     * @param entity
     */
    int updateByCondition(Map<String,Object> entity);

    /**
     * 根据ID 修改
     * @param entity
     */
    int updateById(Map<String,Object> entity);

    Map<String,Object> selectByPrimaryKey(Map<String,Object> param);

    int deleteByCondition(Map<String, Object> param);

    int deleteById(Map<String,Object> param);
    int insertBatch(Map<String, Object> var1);

    int updateByPrimaryKey(Map<String, Object> var1);

    int updateByConditionSelective(Map<String, Object> var1);

    List<Map<String, Object>> selectByCondition(Map<String, Object> var1);

    long countByCondtion(Map<String, Object> var1);

    List<Map<String, Object>> selectBySql(String var1);

    int physicallyDeleted(Map<String, Object> var1);
}
