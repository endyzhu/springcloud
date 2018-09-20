package com.xera.amos.busi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "baseinfo")
public interface BaseinofService {

    @RequestMapping(value = "/say/{message}", method = RequestMethod.GET)
    String sayHi(@PathVariable("message") String message);
}
