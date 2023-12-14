package io.geekidea.boot.auth.service;

import io.geekidea.boot.auth.dto.AccountLoginAppDto;
import io.geekidea.boot.auth.dto.LoginAppDto;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.user.entity.User;

import java.util.Date;

/**
 * @author geekidea
 * @date 2022/7/5
 **/
public interface LoginAppService {

    /**
     * APP小程序登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    LoginTokenVo login(LoginAppDto dto) throws Exception;

    /**
     * APP账号密码登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    LoginTokenVo accountLogin(AccountLoginAppDto dto) throws Exception;

    /**
     * APP登录
     *
     * @param user
     * @return
     * @throws Exception
     */
    LoginTokenVo login(User user) throws Exception;

    /**
     * 刷新登录信息
     *
     * @param user
     * @param token
     * @param lastLoginTime
     * @return
     * @throws Exception
     */
    LoginAppVo refreshLoginInfo(User user, String token, Date lastLoginTime) throws Exception;

    /**
     * 获取登录用户信息
     *
     * @return
     * @throws Exception
     */
    LoginAppVo getLoginUserInfo() throws Exception;

    /**
     * 登出
     *
     * @throws Exception
     */
    void logout() throws Exception;

}
