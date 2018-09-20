package com.xrea.amos.baseinfo.entity;

import com.xrea.amos.baseinfo.base.Column;
import com.xrea.amos.baseinfo.base.Table;

import java.io.Serializable;
import java.util.Date;
@Table(value = "T_ACCOUNT")
public class Account extends BaseEntity implements Serializable {

    private String name;

    private String idCard;

    private String pwd;

    private Date birthDate;

    private Integer age;

    @Column(value = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(value = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    @Column(value = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @Column(value = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    @Column(value = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}