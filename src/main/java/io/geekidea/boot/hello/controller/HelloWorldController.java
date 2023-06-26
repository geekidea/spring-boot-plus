package io.geekidea.boot.hello.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.hello.dto.HelloWorldAddDto;
import io.geekidea.boot.hello.dto.HelloWorldUpdateDto;
import io.geekidea.boot.hello.query.HelloWorldQuery;
import io.geekidea.boot.hello.service.HelloWorldService;
import io.geekidea.boot.hello.vo.HelloWorldInfoVo;
import io.geekidea.boot.hello.vo.HelloWorldVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 你好世界 控制器
 *
 * @author geekidea
 * @since 2023-06-26
 */
@Slf4j
@RestController
@RequestMapping("/helloWorld")
@Tag(name = "你好世界")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    /**
     * 添加你好世界
     *
     * @param helloWorldAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addHelloWorld")
    @Operation(summary = "添加你好世界")
    @Permission("hello:world:add")
    public ApiResult addHelloWorld(@Valid @RequestBody HelloWorldAddDto helloWorldAddDto) throws Exception {
        boolean flag = helloWorldService.addHelloWorld(helloWorldAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改你好世界
     *
     * @param helloWorldUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateHelloWorld")
    @Operation(summary = "修改你好世界")
    @Permission("hello:world:update")
    public ApiResult updateHelloWorld(@Valid @RequestBody HelloWorldUpdateDto helloWorldUpdateDto) throws Exception {
        boolean flag = helloWorldService.updateHelloWorld(helloWorldUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除你好世界
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteHelloWorld/{id}")
    @Operation(summary = "删除你好世界")
    @Permission("hello:world:delete")
    public ApiResult deleteHelloWorld(@PathVariable Long id) throws Exception {
        boolean flag = helloWorldService.deleteHelloWorld(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取你好世界详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getHelloWorld/{id}")
    @Operation(summary = "你好世界详情")
    @Permission("hello:world:info")
    public ApiResult<HelloWorldInfoVo> getHelloWorld(@PathVariable Long id) throws Exception {
        HelloWorldInfoVo helloWorldInfoVo = helloWorldService.getHelloWorldById(id);
        return ApiResult.success(helloWorldInfoVo);
    }

    /**
     * 你好世界分页列表
     *
     * @param helloWorldQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getHelloWorldList")
    @Operation(summary = "你好世界分页列表")
    @Permission("hello:world:list")
    public ApiResult<HelloWorldVo> getHelloWorldList(@Valid @RequestBody HelloWorldQuery helloWorldQuery) throws Exception {
        Paging<HelloWorldVo> paging = helloWorldService.getHelloWorldList(helloWorldQuery);
        return ApiResult.success(paging);
    }

}
