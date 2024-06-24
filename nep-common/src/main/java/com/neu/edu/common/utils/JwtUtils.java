package com.neu.edu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Component
public class JwtUtils {
    /**
     * 密钥
     */
    private static final String SIGN = "Y*(GY*G^&*%69g*()&";

    /**
     * 生成 Token
     *
     * @param userId 登录用户ID
     * @return Token
     */
    public static String createToken(Long userId) {
        // 清除上一个用户信息
        UserContext.removeUser();
        UserContext.removeToken();

        //1.设置过期时间(默认 1 天过期)
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);

        //2.创建 jwt builder，添加自定义的载荷数据
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", userId);

        //3.生成 Token
        //过期时间
        String token = builder.withExpiresAt(instance.getTime())
                // sign
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    /**
     * 验证 Token 合法性
     *
     * @param token
     */
    public static boolean checkToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 获取 Token 信息
     *
     * @param token
     * @return
     */
    public static DecodedJWT parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }

}