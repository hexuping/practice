package com.example.core.httpclient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FirstHttpClient {

    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public static void test() {
        ListOperations<String, String> successList = stringRedisTemplate.opsForList();
        ListOperations<String, String> failList = stringRedisTemplate.opsForList();
        for (int i = 0; i < 100000; i++) {
                if(i < 500) {
                    String customerId = UUID.randomUUID().toString();
                    successList.leftPush("0", customerId);
                } else {
                    String customerId = UUID.randomUUID().toString();
                    failList.leftPush("1", customerId);
                }
        }
    }

//    public static List<Map<String, String>> getCustomerInfo() {
//        stringRedisTemplate.opsForValue().
//    }
}
