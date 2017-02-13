package com.first.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=720000) // 1.session存储的对象必须序列化， 2.前后端分离无sessionID的问题
public class RedisSessionConfig {

}
