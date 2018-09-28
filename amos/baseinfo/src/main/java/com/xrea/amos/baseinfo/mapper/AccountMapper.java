package com.xrea.amos.baseinfo.mapper;

import com.xrea.amos.baseinfo.entity.AccountEntity;

public interface AccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccountEntity record);

    int insertSelective(AccountEntity record);

    AccountEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountEntity record);

    int updateByPrimaryKey(AccountEntity record);
}