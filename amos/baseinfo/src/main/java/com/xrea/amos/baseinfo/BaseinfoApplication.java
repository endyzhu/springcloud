package com.xrea.amos.baseinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients(basePackages = "com.xrea.amos.baseinfo.feign")
@MapperScan("com.xrea.amos.baseinfo.mapper")
public class BaseinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseinfoApplication.class, args);
    }

}
