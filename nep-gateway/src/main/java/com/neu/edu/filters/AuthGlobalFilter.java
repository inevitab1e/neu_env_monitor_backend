package com.neu.edu.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.neu.edu.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

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
        List<String> headers = request.getHeaders().get("authorization");
        if (!CollectionUtils.isEmpty(headers)) {
            token = headers.get(0);
        }
        // 3. 校验并解析token 得到用户ID
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JwtUtils.parseToken(token);
        } catch (Exception e) {
            // token校验失败 拦截响应状态码401
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        Integer userId = decodedJWT.getClaim("userId").asInt();
        // 4. 传递用户信息
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userId.toString()))
                .build();
        // 5. 放行
        return chain.filter(swe);
    }

    private boolean isExcluded(String toString) {
        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
