package io.geekidea.boot.auth.service;

import io.geekidea.boot.auth.vo.LoginRedisVo;
import io.geekidea.boot.auth.vo.LoginVo;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
public interface LoginRedisService {

    /**
     * 获取登录用户token的redis key
     *
     * @param token
     * @return
     * @throws Exception
     */
    String getLoginTokenRedisKey(String token) throws Exception;

    /**
     * 设置登录用户信息到redis
     *
     * @param token
     * @param loginVo
     * @param permissions
     * @throws Exception
     */
    void setLoginRedisVo(String token, LoginVo loginVo, List<String> permissions) throws Exception;

    /**
     * 获取redis中的登录用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    LoginRedisVo getLoginRedisVo(String token) throws Exception;

    /**
     * 删除redis中的登录用户信息
     *
     * @param token
     * @throws Exception
     */
    void deleteLoginRedisVo(String token) throws Exception;

    /**
     * 刷新token
     *
     * @throws Exception
     */
    void refreshToken() throws Exception;

    /**
     * 通过用户token删除当前用户之前的所有的redis登录信息
     *
     * @param token
     * @throws Exception
     */
    void deleteLoginInfoByToken(String token) throws Exception;

}
