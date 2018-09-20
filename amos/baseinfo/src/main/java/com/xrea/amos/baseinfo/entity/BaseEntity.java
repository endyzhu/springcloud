package com.xrea.amos.baseinfo.entity;

import com.xrea.amos.baseinfo.base.Column;
import com.xrea.amos.baseinfo.base.Id;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private String id;

    private String creater;

    private String modifier;

    private Integer isDel;

    private Integer status;

    private Date createtime;

    private Date modifytime;
    @Id(value = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(value = "creater")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
    @Column(value = "modifier")
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    @Column(value = "is_del")
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    @Column(value = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(value = "createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    @Column(value = "modifytime")
    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}
