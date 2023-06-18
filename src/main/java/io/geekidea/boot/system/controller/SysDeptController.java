package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysDeptAddDto;
import io.geekidea.boot.system.dto.SysDeptUpdateDto;
import io.geekidea.boot.system.query.SysDeptQuery;
import io.geekidea.boot.system.service.SysDeptService;
import io.geekidea.boot.system.vo.SysDeptInfoVo;
import io.geekidea.boot.system.vo.SysDeptTreeVo;
import io.geekidea.boot.system.vo.SysDeptVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门 控制器
 *
 * @author liusiyu
 * @since 2022-12-26
 */
@Slf4j
@RestController
@RequestMapping("/sysDept")
@Tag(name = "部门")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 添加部门
     *
     * @param sysDeptAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysDept")
    @Operation(summary = "添加部门")
    @Permission("sys:dept:add")
    public ApiResult addSysDept(@Valid @RequestBody SysDeptAddDto sysDeptAddDto) throws Exception {
        boolean flag = sysDeptService.addSysDept(sysDeptAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改部门
     *
     * @param sysDeptUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysDept")
    @Operation(summary = "修改部门")
    @Permission("sys:dept:update")
    public ApiResult updateSysDept(@Valid @RequestBody SysDeptUpdateDto sysDeptUpdateDto) throws Exception {
        boolean flag = sysDeptService.updateSysDept(sysDeptUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysDept/{id}")
    @Operation(summary = "删除部门")
    @Permission("sys:dept:delete")
    public ApiResult deleteSysDept(@PathVariable Long id) throws Exception {
        boolean flag = sysDeptService.deleteSysDept(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取部门详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDept/{id}")
    @Operation(summary = "部门详情")
    @Permission("sys:dept:info")
    public ApiResult<SysDeptInfoVo> getSysDept(@PathVariable Long id) throws Exception {
        SysDeptInfoVo sysDeptInfoVo = sysDeptService.getSysDeptById(id);
        return ApiResult.success(sysDeptInfoVo);
    }

    /**
     * 部门分页列表
     *
     * @param sysDeptQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDeptList")
    @Operation(summary = "部门分页列表")
    @Permission("sys:dept:list")
    public ApiResult<SysDeptVo> getSysDeptList(@Valid @RequestBody SysDeptQuery sysDeptQuery) throws Exception {
        Paging<SysDeptVo> paging = sysDeptService.getSysDeptList(sysDeptQuery);
        return ApiResult.success(paging);
    }

    /**
     * 部门树形列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDeptTreeList")
    @Operation(summary = "部门树形列表")
    @Permission("sys:dept:tree-list")
    public ApiResult<SysDeptTreeVo> getSysDeptTreeList() throws Exception {
        List<SysDeptTreeVo> list = sysDeptService.getSysDeptTreeList();
        return ApiResult.success(list);
    }

}
