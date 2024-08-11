package com.AnkApp.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(properties = "spring.profiles.active=dev")
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    @Disabled
    public void test(){
        redisTemplate.opsForValue().set("email", "abcd@gmail.com");

        Object email = redisTemplate.opsForValue().get("email");
     }
}
