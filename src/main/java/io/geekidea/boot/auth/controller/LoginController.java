package io.geekidea.boot.auth.controller;

import io.geekidea.boot.auth.dto.LoginDto;
import io.geekidea.boot.auth.service.LoginService;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.common.constant.LoginConstant;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
@RestController
@Tag(name = "管理后台登录")
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 管理后台登录
     *
     * @param loginDto
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @Operation(summary = "管理后台登录")
    public ApiResult<LoginTokenVo> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        LoginTokenVo loginTokenVo = loginService.login(loginDto);
        // 输出token到cookie
        CookieUtil.addCookie(LoginConstant.ADMIN_COOKIE_TOKEN_NAME, loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * 获取管理后台登录用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getLoginUserInfo")
    @Operation(summary = "获取管理后台登录用户信息")
    public ApiResult<LoginVo> getLoginUserInfo() {
        LoginVo loginVo = loginService.getLoginUserInfo();
        return ApiResult.success(loginVo);
    }

    /**
     * 管理后台退出
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    @Operation(summary = "管理后台退出")
    public ApiResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        // 删除缓存
        loginService.logout();
        // 从cookie中删除token
        CookieUtil.deleteCookie(LoginConstant.ADMIN_COOKIE_TOKEN_NAME, request, response);
        return ApiResult.success();
    }

}
