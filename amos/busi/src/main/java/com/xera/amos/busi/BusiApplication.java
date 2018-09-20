package com.xera.amos.busi;

import com.xera.amos.busi.feign.BaseinofService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.invoke.MethodType;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BusiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusiApplication.class, args);
    }

    @RequestMapping(value = "/say/{message}",method = RequestMethod.GET)
    public String sayHi(@PathVariable("message") String message){
        return "hi,I'm from busi,"+message;
    }
}
