package com.xrea.amos.baseinfo.entity;

import com.xrea.amos.baseinfo.base.Column;
import com.xrea.amos.baseinfo.base.Id;
import com.xrea.amos.baseinfo.base.SystemConst;
import com.xrea.amos.baseinfo.util.CommUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhuqb
 */
@Data
public class BaseEntity implements Serializable {

    private String id;

    private String creater;

    private String modifier;

    private Integer isDel = SystemConst.IsDelEnum.IS_DEL_NO.getIndex();

    private Integer status = SystemConst.StatusEnum.STATUS_ACTIVE.getindex();

    private Date createtime = new Date();

    private Date modifytime = new Date();

    /**
     * 判断是否是新的
     * @return true 是新的  false 不是新的
     */
    public boolean isNew(){
        if (CommUtil.isNullOrEmpty(this.id)){
            return true;
        }
        return false;
    }

}
