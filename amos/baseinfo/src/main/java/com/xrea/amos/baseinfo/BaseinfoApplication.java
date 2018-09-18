package com.xrea.amos.baseinfo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaseinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseinfoApplication.class, args);
    }

    OkHttpClient okHttpClient = new OkHttpClient();
    final Request request = new Request.Builder().url("").get().build();
}
