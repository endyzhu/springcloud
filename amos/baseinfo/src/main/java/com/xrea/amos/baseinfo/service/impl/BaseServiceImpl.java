package com.xrea.amos.baseinfo.service.impl;

import com.xrea.amos.baseinfo.base.IgnoreColumn;
import com.xrea.amos.baseinfo.base.SystemConst;
import com.xrea.amos.baseinfo.base.Table;
import com.xrea.amos.baseinfo.entity.BaseEntity;
import com.xrea.amos.baseinfo.mapper.BaseMapper;
import com.xrea.amos.baseinfo.service.BaseService;
import com.xrea.amos.baseinfo.util.ClsUtil;
import com.xrea.amos.baseinfo.util.CommUtil;
import com.xrea.amos.baseinfo.util.IdGenUtils;
import com.xrea.amos.baseinfo.util.WhereCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 通用方法
 *
 * @param <T>
 */
@Service(value = "baseService")
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private BaseMapper baseMapper;

    /**
     * 通过实体类获取表名、列段和值得数据结构
     *
     * @param entity
     * @return
     */
    public <T extends BaseEntity> Map<String, Object> getInsertTableAndColumnByEntityAnnotation(T entity) {
        Map<String, Object> map = new HashMap<String, Object>(128);
        //获取表名
        if (null == entity.getClass().getAnnotation(Table.class)) {
            throw new RuntimeException("实体类：" + entity.getClass().getName() + "没有添加@Table注释");
        }
        //操作表名称
        map.put(SystemConst.MyBatis.TABLE_NAME, entity.getClass().getAnnotation(Table.class).value());
        //获取实体类以及父类中的属性
        List<Field> fields = ClsUtil.getAndSuperField(entity.getClass());
        // 存储字段名
        List<String> columnList = new ArrayList<String>();
        List<Object> columnValueList = new ArrayList<Object>();

        if (CommUtil.isNotNullOrEmpty(fields)) {
            try {
                for (Field field : fields) {
                    //排除添加IgnoreColumn 字段
                    if (null != field.getAnnotation(IgnoreColumn.class)) {

                    } else {
                        //将属性中的驼峰转下划线
                        columnList.add(CommUtil.camelToUnderline(field.getName()));
                        Method m = (Method) entity.getClass().getMethod(
                                "get" + CommUtil.getMethodName(field.getName()));

                        columnValueList.add(m.invoke(entity, null));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("解析出现问题" + e.getMessage());
            }
        }
        map.put(SystemConst.MyBatis.COLUMN_NAME, columnList);
        map.put(SystemConst.MyBatis.COLUMN_VALUE, columnValueList);
        if (columnList.size() != columnValueList.size()) {
            throw new RuntimeException("解析出现问题，字段和值数量不一样");
        }
        return map;
    }

    /**
     * 获取修改的字段和值
     * @param entity
     * @return
     */
    public <T extends BaseEntity> Map<String,Object> getUpdateTableAndColumnByEntityAnnotation(T entity){
        Map<String, Object> map = new HashMap<String, Object>(128);
        //获取表名
        if (null == entity.getClass().getAnnotation(Table.class)) {
            throw new RuntimeException("实体类：" + entity.getClass().getName() + "没有添加@Table注释");
        }
        //操作表名称
        map.put(SystemConst.MyBatis.TABLE_NAME, entity.getClass().getAnnotation(Table.class).value());
        //获取实体类以及父类中的属性
        List<Field> fields = ClsUtil.getAndSuperField(entity.getClass());
        // where 条件
        Map<String,Object> updateColumnValueMap = new HashMap<>(128);
        if (CommUtil.isNotNullOrEmpty(fields)) {
            try {
                for (Field field : fields) {
                    if (!SystemConst.MyBatis.ID.toLowerCase().equals(field.getName())){
                        //排除添加IgnoreColumn 字段
                        if (null != field.getAnnotation(IgnoreColumn.class)) {

                        } else {
                            //将属性中的驼峰转下划线
                            Method m = (Method) entity.getClass().getMethod(
                                    "get" + CommUtil.getMethodName(field.getName()));
                            updateColumnValueMap.put(CommUtil.camelToUnderline(field.getName()),m.invoke(entity,null));
                        }
                    }else{
                        Method m = (Method) entity.getClass().getMethod(
                                "get" + CommUtil.getMethodName(field.getName()));
                        //更新的Id
                        map.put(SystemConst.MyBatis.ID,m.invoke(entity,null));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("解析出现问题" + e.getMessage());
            }
        }
        if (CommUtil.isNullOrEmpty(updateColumnValueMap)){
            throw new RuntimeException("更新的条件不能为空");
        }
        map.put(SystemConst.MyBatis.UPDATE_COLUM_VALUE,updateColumnValueMap);
        return map;
    }

    /**
     * 保存对象
     *
     * @param entity
     * @return
     */
    @Override
    public <T extends BaseEntity> T save(T entity) {
        if (CommUtil.isNullOrEmpty(entity.getId())) {
            String id = IdGenUtils.getUUID();
            entity.setId(id);
            //TODO 待集成登录权限验证
            entity.setCreater("");
            Map<String, Object> map = getInsertTableAndColumnByEntityAnnotation(entity);
            int i = baseMapper.insert(map);
            return i == 1 ? entity : null;
        } else {
            //TODO 待集成登录权限验证
            entity.setModifier("");
            entity.setModifytime(new Date());
            Map<String, Object> map = getUpdateTableAndColumnByEntityAnnotation(entity);
            int i = baseMapper.updateById(map);
            return i == 1 ? entity : null;
        }
    }

    /**
     * 根据条件来修改
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int updateByCondition(T entity,WhereCondition condition){
        Map<String, Object> map = getUpdateTableAndColumnByEntityAnnotation(entity);
        if (CommUtil.isNullOrEmpty(map)) {
            throw new RuntimeException("解析实体类："+entity.getClass().getName()+" 出错");
        }
        map.put(SystemConst.MyBatis.WHERE_PARAM,condition.build());
        map.put(SystemConst.MyBatis.UPDATE_COLUM_VALUE,condition.getParams());
        return baseMapper.updateByCondition(map);
    }
    /**
     * 根据ID 删除记录
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int deleteById(T entity) {
        Map<String, Object> map = new HashMap<String, Object>(128);
        //获取表名
        if (null == entity.getClass().getAnnotation(Table.class)) {
            throw new RuntimeException("实体类：" + entity.getClass().getName() + "没有添加@Table注释");
        }
        //操作表名称
        map.put(SystemConst.MyBatis.TABLE_NAME, entity.getClass().getAnnotation(Table.class).value());
        map.put(SystemConst.MyBatis.ID,entity.getId());
        return baseMapper.deleteByCondition(map);
    }

    /**
     * 根据条件 删除记录
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int deleteByCondition(T entity, WhereCondition condition) {
        Map<String, Object> map = new HashMap<String, Object>(128);
        //获取表名
        if (null == entity.getClass().getAnnotation(Table.class)) {
            throw new RuntimeException("实体类：" + entity.getClass().getName() + "没有添加@Table注释");
        }
        //操作表名称
        map.put(SystemConst.MyBatis.TABLE_NAME, entity.getClass().getAnnotation(Table.class).value());
        map.put(SystemConst.MyBatis.WHERE_PARAM,condition.build());
        map.put(SystemConst.MyBatis.UPDATE_COLUM_VALUE,condition.getParams());
        return baseMapper.deleteByCondition(map);
    }





}
