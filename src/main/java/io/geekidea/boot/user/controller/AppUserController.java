package io.geekidea.boot.user.controller;

import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.user.dto.AppUserHeadDto;
import io.geekidea.boot.user.dto.AppUserNicknameDto;
import io.geekidea.boot.user.service.UserService;
import io.geekidea.boot.user.vo.AppUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AppUserController {

    @Autowired
    private UserService userService;

    /**
     * 获取App用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getProfile")
    @Operation(summary = "获取App用户信息")
    public ApiResult<AppUserVo> getProfile() {
        AppUserVo appUserVo = userService.getProfile();
        return ApiResult.success(appUserVo);
    }

    /**
     * 修改用户头像
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateHead")
    @Operation(summary = "修改用户头像")
    public ApiResult<Boolean> updateHead(@Valid @RequestBody AppUserHeadDto dto) {
        boolean flag = userService.updateHead(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改昵称
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateNickname")
    @Operation(summary = "修改昵称")
    public ApiResult<AppUserVo> updateNickname(@Valid @RequestBody AppUserNicknameDto dto) {
        boolean flag = userService.updateNickname(dto);
        return ApiResult.result(flag);
    }

}
