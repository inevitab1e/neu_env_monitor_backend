package com.neu.edu.filters;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.neu.edu.common.redis.RedisUtils;
import com.neu.edu.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final RedisUtils redisUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取request对象
        ServerHttpRequest request = exchange.getRequest();
        // 判断是否需要登录拦截（测试打开）
        if (isExcluded(request.getPath().toString())) {
            // 放行
            return chain.filter(exchange);
        }
        // 2. 获取token
        String token = null;
        List<String> headers = request.getHeaders().get("user_info");
        if (!CollectionUtils.isEmpty(headers)) {
            token = headers.get(0);
        }
        // 3. 校验并解析token 得到用户ID
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JwtUtils.parseToken(token);
        } catch (Exception e) {
            return unauthorizedResponse(exchange);
        }

        // 校验redis中的token
        try {
            String redisToken = (String) redisUtils.get(token);
            // redis中没有该token 或 与前端传来的token不同
            if (StrUtil.isEmpty(redisToken) || !redisToken.equals(token)) {
                return unauthorizedResponse(exchange);
            }
        } catch (Exception e) {
            return unauthorizedResponse(exchange);
        }

        // 4. 传递用户信息
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user_info", decodedJWT.getToken()))
                .build();
        // 5. 放行
        return chain.filter(swe);
    }

    // 设置未授权响应
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    // 排除路径
    private boolean isExcluded(String toString) {
        ArrayList<String> paths = new ArrayList<>();
        paths.add("/nep/admin/login");
        paths.add("/nep/supervisor/send_msg");
        paths.add("/nep/supervisor/login_by_pwd");
        paths.add("/nep/supervisor/login_by_sms");
        paths.add("/nep/supervisor/sign_up");
        paths.add("/nep/supervisor/send_msg");
        paths.add("/nep/grid/login_by_pwd");
        paths.add("/nep/grid/login_by_sms");
        paths.add("/nep/statistics/get_province_aqi_index_exceeded_info");
        paths.add("/nep/statistics/get_aqi_count_info");
        paths.add("/nep/statistics/get_aqi_month_count_info");
        paths.add("/nep/statistics/get_coverage_info");
        paths.add("/nep/statistics/get_summary_data_info");

        if (paths.contains(toString)) {
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
