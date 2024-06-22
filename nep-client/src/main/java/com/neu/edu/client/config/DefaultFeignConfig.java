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
                String userId = UserContext.getUser().toString();
                if (StrUtil.isNotBlank(userId)) {
                    template.header("user-info", userId);
                }
            }
        };
    }
}
