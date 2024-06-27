package com.neu.edu.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.neu.edu.common.utils.JwtUtils;
import com.neu.edu.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Fei Bo
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取登录用户信息
        String userInfo = request.getHeader("user_info");
        // 2. 判断是否获取了用户
        if (StrUtil.isNotBlank(userInfo)) {
            Long userId = JwtUtils.parseToken(userInfo).getClaim("userId").asLong();
            // 将当前用户信息存入线程
            UserContext.setUserId(userId);
            UserContext.setToken(userInfo);
        }
        // 3. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 4. 清理用户信息
        UserContext.removeUserId();
        UserContext.removeToken();
    }
}
