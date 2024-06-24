package com.neu.edu.client.config;

import cn.hutool.core.util.StrUtil;
import com.neu.edu.common.utils.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 获取当前用户token
                String token = UserContext.getToken();
                // 如果token存在 则将openfeign发出的请求请求头加上当前的token 从而使得有权访问需要调用的方法
                if (StrUtil.isNotBlank(token)) {
                    template.header("user-info", UserContext.getToken());
                }
            }
        };
    }
}
