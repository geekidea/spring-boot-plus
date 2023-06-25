package io.geekidea.boot.auth.controller;

import io.geekidea.boot.auth.constant.LoginConstant;
import io.geekidea.boot.auth.dto.LoginDto;
import io.geekidea.boot.auth.service.LoginService;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.framework.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
@RestController
@Tag(name = "登录登出")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    public ApiResult<LoginTokenVo> login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {
        LoginTokenVo loginTokenVo = loginService.login(loginDto);
        // 输出token到cookie
        Cookie cookie = new Cookie(LoginConstant.TOKEN_NAME, loginTokenVo.getToken());
        response.addCookie(cookie);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * 获取登录用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getLoginUserInfo")
    @Operation(summary = "获取登录用户信息")
    public ApiResult<LoginVo> getLoginUserInfo() throws Exception {
        LoginVo loginVo = loginService.getLoginUserInfo();
        return ApiResult.success(loginVo);
    }

    /**
     * 退出
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    @Operation(summary = "退出")
    public ApiResult<Boolean> logout() throws Exception {
        loginService.logout();
        return ApiResult.success();
    }


}
