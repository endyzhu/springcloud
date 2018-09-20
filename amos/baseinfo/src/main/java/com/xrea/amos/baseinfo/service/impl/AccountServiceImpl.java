package com.xrea.amos.baseinfo.service.impl;

import com.xrea.amos.baseinfo.entity.BaseEntity;
import com.xrea.amos.baseinfo.service.AccountService;
import com.xrea.amos.baseinfo.service.BaseService;
import org.springframework.stereotype.Service;

@Service(value = "accountService")
public class AccountServiceImpl<Account extends BaseEntity> extends BaseServiceImpl<Account> implements AccountService<Account> {

}
