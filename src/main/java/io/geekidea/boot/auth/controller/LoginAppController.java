package io.geekidea.boot.auth.controller;

import io.geekidea.boot.auth.dto.AccountLoginAppDto;
import io.geekidea.boot.auth.dto.LoginAppDto;
import io.geekidea.boot.auth.service.LoginAppService;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.common.constant.LoginConstant;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.util.CookieUtil;
import io.geekidea.boot.util.HttpServletRequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
@RestController
@Tag(name = "APP登录")
@RequestMapping("/app")
public class LoginAppController {

    @Autowired
    private LoginAppService loginAppService;

    /**
     * APP小程序登录
     *
     * @param loginAppDto
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @Operation(summary = "APP小程序登录")
    public ApiResult<LoginTokenVo> login(@Valid @RequestBody LoginAppDto loginAppDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginTokenVo loginTokenVo = loginAppService.login(loginAppDto);
        // 输出token到cookie
        addCookie(loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * APP账号密码登录
     *
     * @param loginAccountAppDto
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/accountLogin")
    @Operation(summary = "APP账号密码登录")
    public ApiResult<LoginTokenVo> accountLogin(@Valid @RequestBody AccountLoginAppDto loginAccountAppDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginTokenVo loginTokenVo = loginAppService.accountLogin(loginAccountAppDto);
        // 输出token到cookie
        addCookie(loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * 获取APP登录用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getLoginUserInfo")
    @Operation(summary = "获取APP登录用户信息")
    public ApiResult<LoginAppVo> getLoginUserInfo() throws Exception {
        LoginAppVo loginAppVo = loginAppService.getLoginUserInfo();
        return ApiResult.success(loginAppVo);
    }

    /**
     * APP退出
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    @Operation(summary = "APP退出")
    public ApiResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        loginAppService.logout();
        // 从cookie中删除token
        CookieUtil.deleteCookie(LoginConstant.APP_COOKIE_TOKEN_NAME, request, response);
        return ApiResult.success();
    }

    /**
     * 输出token到cookie
     *
     * @param token
     * @param request
     * @param response
     */
    private void addCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        boolean docRequest = HttpServletRequestUtil.isDocRequest(request);
        if (docRequest) {
            CookieUtil.addCookie(LoginConstant.APP_COOKIE_TOKEN_NAME, token, request, response);
        }
    }

}
