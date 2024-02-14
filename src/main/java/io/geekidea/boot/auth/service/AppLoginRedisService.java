package io.geekidea.boot.auth.service;

import io.geekidea.boot.auth.vo.AppLoginVo;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
public interface AppLoginRedisService {

    /**
     * 获取登录用户token的redis key
     *
     * @param token
     * @return
     * @throws Exception
     */
    String getLoginRedisKey(String token);

    /**
     * 设置登录用户信息到redis
     *
     * @param token
     * @param appLoginVo
     * @throws Exception
     */
    void setLoginVo(String token, AppLoginVo appLoginVo);

    /**
     * 获取redis中的登录用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    AppLoginVo getLoginVo(String token);

    /**
     * 删除redis中的登录用户信息
     *
     * @param token
     * @throws Exception
     */
    void deleteLoginVo(String token);

    /**
     * 刷新token
     *
     * @throws Exception
     */
    void refreshToken();

    /**
     * 通过用户token删除当前用户之前的所有的redis登录信息
     *
     * @param token
     * @throws Exception
     */
    void deleteLoginInfoByToken(String token);

}
