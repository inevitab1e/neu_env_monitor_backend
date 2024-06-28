package com.neu.edu;

import com.neu.edu.common.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class NepAdminApplicationTests {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {
        redisUtils.set("insert-test", "测试");
//        redisTemplate.opsForValue().set("insert-test", "测试");
        Object object = redisUtils.get("insert-test");
//        Object object = redisTemplate.opsForValue().get("insert-test");
        System.out.println(object);
//        redisUtils.test();
    }
}
