package com.neu.edu.common.utils;

public class UserContext {
    private static final ThreadLocal<Long> tl_userId = new ThreadLocal<>();
    private static final ThreadLocal<String> tl_token = new ThreadLocal<>();

    /**
     * 保存当前登录用户id信息到ThreadLocal
     *
     * @param userId 用户id
     */
    public static void setUser(Long userId) {
        tl_userId.set(userId);
    }

    /**
     * 获取当前登录用户id信息
     *
     * @return 用户id
     */
    public static Long getUser() {
        return tl_userId.get();
    }

    /**
     * 移除当前登录用户信息
     */
    public static void removeUser() {
        tl_userId.remove();
    }

    /**
     * 保存当前登录用户id信息到ThreadLocal
     *
     * @param token 用户token
     */
    public static void setToken(String token) {
        tl_token.set(token);
    }

    /**
     * 获取当前登录用户id信息
     *
     * @return 用户token
     */
    public static String getToken() {
        return tl_token.get();
    }

    /**
     * 移除当前登录用户token信息
     */
    public static void removeToken() {
        tl_token.remove();
    }

}
