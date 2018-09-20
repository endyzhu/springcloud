package com.xrea.amos.baseinfo.web;

import com.xrea.amos.baseinfo.entity.Account;
import com.xrea.amos.baseinfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private AccountService AccountService;

    @RequestMapping(value = "/saveAccount",method = RequestMethod.GET)
    public String saveAccount(){
        Account account = new Account();
        account.setId(UUID.randomUUID().toString().replaceAll("-",""));
        account.setName("endy");
        account.setAge(30);
        account.setCreatetime(new Date());
        AccountService.insert(account);
        return "";
    }
}
