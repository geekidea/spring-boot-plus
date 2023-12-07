package io.geekidea.boot.auth.cache;

import io.geekidea.boot.auth.vo.LoginRedisVo;

/**
 * 在当前线程中缓存管理后台登录信息
 * 如果开启多线程需要获取
 *
 * @author geekidea
 * @date 2023/12/7
 **/
public class LoginCache {

    /**
     * 当前线程中保存管理后台登录信息
     */
    private static final ThreadLocal<LoginRedisVo> LOGIN_CACHE = new ThreadLocal<>();

    /**
     * 设置管理后台登录信息到当前线程中
     *
     * @param loginRedisVo
     */
    public static void set(LoginRedisVo loginRedisVo) {
        LOGIN_CACHE.set(loginRedisVo);
    }

    /**
     * 从当前线程获取管理后台登录信息
     *
     * @return
     */
    public static LoginRedisVo get() {
        return LOGIN_CACHE.get();
    }

    /**
     * 从当前线程中移除管理后台登录信息
     */
    public static void remove() {
        LOGIN_CACHE.remove();
    }

}
