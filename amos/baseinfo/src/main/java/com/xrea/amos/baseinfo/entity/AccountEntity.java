package com.xrea.amos.baseinfo.entity;

import com.xrea.amos.baseinfo.base.IgnoreColumn;
import com.xrea.amos.baseinfo.base.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Table(value = "T_ACCOUNT")
@Data
public class AccountEntity extends BaseEntity implements Serializable {

    private String name;

    private String idCard;

    private String pwd;

    private Date birthDate;

    private Integer age;
    //将该属性从数据库中排除
    @IgnoreColumn
    private String dd;
}