package io.geekidea.boot.auth.service;

import io.geekidea.boot.auth.dto.AccountLoginAppDto;
import io.geekidea.boot.auth.dto.LoginAppDto;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.user.entity.User;

/**
 * @author geekidea
 * @date 2022/7/5
 **/
public interface LoginAppService {

    /**
     * APP小程序登录
     *
     * @param loginAppDto
     * @return
     * @throws Exception
     */
    LoginTokenVo login(LoginAppDto loginAppDto) throws Exception;

    /**
     * APP账号密码登录
     *
     * @param loginAccountAppDto
     * @return
     * @throws Exception
     */
    LoginTokenVo accountLogin(AccountLoginAppDto loginAccountAppDto) throws Exception;

    /**
     * APP登录
     *
     * @param user
     * @return
     * @throws Exception
     */
    LoginTokenVo login(User user) throws Exception;

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
