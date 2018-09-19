package com.xrea.amos.baseinfo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//session 超时时间
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
public class RedisSessionConfig {
}
