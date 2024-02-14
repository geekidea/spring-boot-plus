package io.geekidea.boot.auth.cache;

import io.geekidea.boot.auth.vo.AppLoginVo;

/**
 * 在当前线程中缓存token
 * 如果开启多线程需要获取
 *
 * @author geekidea
 * @date 2023/12/7
 **/
public class AppLoginCache {

    /**
     * 当前线程中保存APP移动端登录信息
     */
    private static final ThreadLocal<AppLoginVo> APP_LOGIN_CACHE = new ThreadLocal<>();

    /**
     * 设置APP移动端登录信息到当前线程中
     *
     * @param appLoginVo
     */
    public static void set(AppLoginVo appLoginVo) {
        APP_LOGIN_CACHE.set(appLoginVo);
    }

    /**
     * 从当前线程获取APP移动端登录信息
     *
     * @return
     */
    public static AppLoginVo get() {
        return APP_LOGIN_CACHE.get();
    }

    /**
     * 从当前线程中移除APP移动端登录信息
     */
    public static void remove() {
        APP_LOGIN_CACHE.remove();
    }

}
