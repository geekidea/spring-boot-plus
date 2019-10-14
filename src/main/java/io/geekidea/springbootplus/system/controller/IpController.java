package io.geekidea.springbootplus.system.controller;

import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.system.service.IpService;
import io.geekidea.springbootplus.system.param.IpQueryParam;
import io.geekidea.springbootplus.system.vo.IpQueryVo;
import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.common.param.IdParam;

/**
 * <p>
 * IP地址 前端控制器
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
@Slf4j
@RestController
@RequestMapping("/ip")
@Api("IP地址 API")
public class IpController extends BaseController {

    @Autowired
    private IpService ipService;

    /**
     * 添加IP地址
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加Ip对象", notes = "添加IP地址", response = ApiResult.class)
    public ApiResult<Boolean> addIp( @RequestBody Ip ip) throws Exception {
        boolean flag = ipService.save(ip);
        return ApiResult.result(flag);
    }

    /**
     * 修改IP地址
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改Ip对象", notes = "修改IP地址", response = ApiResult.class)
    public ApiResult<Boolean> updateIp( @RequestBody Ip ip) throws Exception {
        boolean flag = ipService.updateById(ip);
        return ApiResult.result(flag);
    }

    /**
     * 删除IP地址
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除Ip对象", notes = "删除IP地址", response = ApiResult.class)
    public ApiResult<Boolean> deleteIp( @RequestBody IdParam idParam) throws Exception {
        boolean flag = ipService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
     * 获取IP地址
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取Ip对象详情", notes = "查看IP地址", response = IpQueryVo.class)
    public ApiResult<IpQueryVo> getIp( @RequestBody IdParam idParam) throws Exception {
        IpQueryVo ipQueryVo = ipService.getIpById(idParam.getId());
        return ApiResult.ok(ipQueryVo);
    }

    /**
     * IP地址分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取Ip分页列表", notes = "IP地址分页列表", response = IpQueryVo.class)
    public ApiResult<Paging<IpQueryVo>> getIpPageList( @RequestBody IpQueryParam ipQueryParam) throws Exception {
        Paging<IpQueryVo> paging = ipService.getIpPageList(ipQueryParam);
        return ApiResult.ok(paging);
    }

}

