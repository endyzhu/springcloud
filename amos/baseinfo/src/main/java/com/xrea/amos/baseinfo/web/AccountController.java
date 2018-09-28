package com.xrea.amos.baseinfo.web;

import com.xrea.amos.baseinfo.entity.AccountEntity;
import com.xrea.amos.baseinfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private AccountService AccountService;

    @RequestMapping(value = "/saveAccount", method = RequestMethod.GET)
    public String saveAccount() {
        AccountEntity account = new AccountEntity();
        account.setId("b262b57087854afeb9fe48374787a78d");
        account.setName("endy1111");
        account.setAge(300);
        AccountService.save(account);
        return "";
    }
}
