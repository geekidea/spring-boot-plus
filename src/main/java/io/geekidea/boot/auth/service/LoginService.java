package io.geekidea.boot.auth.service;

import io.geekidea.boot.auth.dto.LoginDto;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.auth.vo.LoginTokenVo;

/**
 * @author geekidea
 * @date 2022/7/5
 **/
public interface LoginService {

    /**
     * 登录
     *
     * @param loginDto
     * @return
     * @throws Exception
     */
    LoginTokenVo login(LoginDto loginDto) throws Exception;

    /**
     * 获取登录用户信息
     *
     * @return
     * @throws Exception
     */
    LoginVo getLoginUserInfo() throws Exception;

    /**
     * 登出
     *
     * @throws Exception
     */
    void logout() throws Exception;

}
