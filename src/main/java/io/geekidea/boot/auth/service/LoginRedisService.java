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
    String getLoginRedisKey(String token) throws Exception;

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
     * 获取登录用户ID
     *
     * @param token
     * @return
     * @throws Exception
     */
    Long getLoginUserId(String token) throws Exception;

    /**
     * 通过用户ID删除当前用户所有的redis登录信息
     *
     * @param userId
     * @throws Exception
     */
    void deleteLoginInfoByUserId(Long userId) throws Exception;

}
