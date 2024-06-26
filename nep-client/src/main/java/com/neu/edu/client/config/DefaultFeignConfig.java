package com.neu.edu.client.config;

import cn.hutool.core.util.StrUtil;
import com.neu.edu.client.client.fallback.*;
import com.neu.edu.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    @Bean
    public Logger.Level fullFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 获取当前用户token
                String token = UserContext.getToken();
                // 如果token存在 则将openfeign发出的请求请求头加上当前的token 从而使得有权访问需要调用的方法
                if (StrUtil.isNotBlank(token)) {
                    template.header("user_info", UserContext.getToken());
                }
            }
        };
    }

    @Bean
    public AqiClientFallbackFactory aqiClientFallbackFactory() {
        return new AqiClientFallbackFactory();
    }

    @Bean
    public AqiFeedbackClientFallbackFactory aqiFeedbackClientFallbackFactory() {
        return new AqiFeedbackClientFallbackFactory();
    }

    @Bean
    public GridClientFallbackFactory gridClientFallbackFactory() {
        return new GridClientFallbackFactory();
    }

    @Bean
    public LocationClientFallbackFactory locationClientFallbackFactory() {
        return new LocationClientFallbackFactory();
    }

    @Bean
    public StatisticsClientFallbackFactory statisticsClientFallbackFactory() {
        return new StatisticsClientFallbackFactory();
    }

    @Bean
    public SupervisorClientFallbackFactory supervisorClientFallbackFactory() {
        return new SupervisorClientFallbackFactory();
    }
}
