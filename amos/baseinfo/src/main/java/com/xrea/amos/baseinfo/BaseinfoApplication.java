package com.xrea.amos.baseinfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BaseinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseinfoApplication.class, args);
    }
    @Value("${foo.test}")
    String foo;
    @RequestMapping("/sayhi")
    public String sayHi(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userinfo");
        if (StringUtils.isEmpty(username)){
            username = ""+System.currentTimeMillis();
            request.setAttribute("userinfo",username);
        }
        return "hello " + username;
    }
}
