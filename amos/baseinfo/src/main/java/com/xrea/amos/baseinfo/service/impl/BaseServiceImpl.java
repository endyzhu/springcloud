package com.xrea.amos.baseinfo.service.impl;

import com.xrea.amos.baseinfo.base.Column;
import com.xrea.amos.baseinfo.base.Id;
import com.xrea.amos.baseinfo.base.SystemConst;
import com.xrea.amos.baseinfo.base.Table;
import com.xrea.amos.baseinfo.entity.BaseEntity;
import com.xrea.amos.baseinfo.mapper.BaseMapper;
import com.xrea.amos.baseinfo.service.BaseService;
import com.xrea.amos.baseinfo.util.IdGenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 通用方法
 * @param <T>
 *
 */
@Service(value = "baseService")
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private BaseMapper baseMapper;

    /**
     * 通过实体类获取表名、列段和值得数据结构
     * @param entity
     * @return
     */
    public Map<String,Object> getTableAndColumnByEntityAnnotation(T entity){
        Map<String,Object> map = new HashMap<String,Object>(10);
        //获取表名
        if( null == entity.getClass().getAnnotation(Table.class)){
            throw new RuntimeException("实体类："+entity.getClass().getName()+"没有添加@Table注释");
        }
        map.put(SystemConst.TABLE_NAME,entity.getClass().getAnnotation(Table.class).value());
        //获取实体类中的方法
        Method[] methods = entity.getClass().getMethods();
        // 存储字段名
        List<String> columnList = new ArrayList<String>();
        List<Object> columnValueList = new ArrayList<Object>();
        if (null != methods && methods.length > 0){
            for (Method method : methods){
                try{
                    //获取字段和对应的值
                    if (null != method.getAnnotation(Column.class)){
                        columnList.add(method.getAnnotation(Column.class).value());
                        columnValueList.add(method.invoke(entity,null));
                    }
                    //主键
                    if (null != method.getAnnotation(Id.class)){
                        columnList.add(method.getAnnotation(Id.class).value());
                        columnValueList.add(method.invoke(entity,null));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException("解析出现问题");
                }
            }
        }else{
            throw new RuntimeException("实体类："+entity.getClass().getName()+"没有对应的方法");
        }
        map.put(SystemConst.COLUMN_NAME,columnList);
        map.put(SystemConst.COLUMN_VALUE,columnValueList);
        if (columnList.size() != columnValueList.size()){
            throw new RuntimeException("解析出现问题，字段和值数量不一样!");
        }
        return map;
    }

    /**
     * 保存对象
     * @param entity
     * @return
     */
    @Override
    public String save(T entity){
        if (StringUtils.isEmpty(entity.getId())){
            String id = IdGenUtils.getUUID();
            entity.setId(id);
            entity.setCreater("");
            entity.setCreatetime(new Date());
            entity.setIsDel(SystemConst.IS_DEL_NO);
            entity.setStatus(SystemConst.STATUS_ACTIVE);
            Map<String,Object> map = getTableAndColumnByEntityAnnotation(entity);
            baseMapper.insert(map);
            return id;
        }else{
            entity.setModifier("");
            entity.setModifytime(new Date());
            return entity.getId();
        }
    }

}
