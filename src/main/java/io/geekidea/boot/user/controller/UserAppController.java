package io.geekidea.boot.user.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.user.query.UserAppQuery;
import io.geekidea.boot.user.service.UserService;
import io.geekidea.boot.user.vo.UserAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App用户信息 控制器
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "App用户信息")
@RequestMapping("/app/user")
public class UserAppController {

    @Autowired
    private UserService userService;

    /**
     * 获取App用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppUser/{id}")
    @Operation(summary = "App用户信息详情")
    public ApiResult<UserAppVo> getAppUser(@PathVariable Long id) throws Exception {
        UserAppVo userAppVo = userService.getAppUserById(id);
        return ApiResult.success(userAppVo);
    }

    /**
     * App用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppUserPage")
    @Operation(summary = "App用户信息分页列表")
    public ApiResult<UserAppVo> getAppUserPage(@Valid @RequestBody UserAppQuery query) throws Exception {
        Paging<UserAppVo> paging = userService.getAppUserPage(query);
        return ApiResult.success(paging);
    }

}
