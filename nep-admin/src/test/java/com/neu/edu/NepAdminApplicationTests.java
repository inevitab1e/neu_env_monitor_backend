package com.neu.edu;

import com.neu.edu.common.redis.RedisUtils;
import com.neu.edu.common.utils.SMSUtils;
import com.neu.edu.common.utils.ValidateCodeUtils;
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

    @Test
    void testSMS() {
        String code = ValidateCodeUtils.generateValidateCode(6).toString();
        System.out.println(code);
        SMSUtils.sendMessage("NEP", "SMS_468695259", "13555949265", code);
    }
}
