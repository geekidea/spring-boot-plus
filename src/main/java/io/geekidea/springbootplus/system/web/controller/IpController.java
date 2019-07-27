package io.geekidea.springbootplus.system.web.controller;

import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.system.service.IpService;
import io.geekidea.springbootplus.system.web.param.IpQueryParam;
import io.geekidea.springbootplus.system.web.vo.IpQueryVo;
import io.geekidea.springbootplus.common.web.controller.BaseController;
import io.geekidea.springbootplus.common.api.ApiResult;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.geekidea.springbootplus.common.web.vo.Paging;
import io.geekidea.springbootplus.common.web.param.IdParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author geekidea
 * @since 2019-07-27
 */
@RestController
@RequestMapping("/ip")
@Slf4j
@Api(" API")
public class IpController extends BaseController {

    @Autowired
    private IpService ipService;

    /**
    * 添加
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加Ip对象",notes = "添加",response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Valid @RequestBody Ip ip) throws Exception{
        boolean flag = ipService.save(ip);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改Ip对象",notes = "修改",response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Valid @RequestBody Ip ip) throws Exception{
        boolean flag = ipService.updateById(ip);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除Ip对象",notes = "删除",response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@Valid @RequestBody IdParam idParam) throws Exception{
        boolean flag = ipService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
    * 获取
    */
    @PostMapping("/info")
    @ApiOperation(value = "获取Ip对象详情",notes = "查看",response = IpQueryVo.class)
    public ApiResult<IpQueryVo> getSysUser(@Valid @RequestBody IdParam idParam) throws Exception{
        IpQueryVo ipQueryVo = ipService.getIpById(idParam.getId());
        return ApiResult.ok(ipQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取Ip分页列表",notes = "分页列表",response = IpQueryVo.class)
    public ApiResult<Paging<IpQueryVo>> getIpPageList(@Valid @RequestBody(required = false) IpQueryParam ipQueryParam) throws Exception{
        Paging<IpQueryVo> paging = ipService.getIpPageList(ipQueryParam);
        return ApiResult.ok(paging);
    }

}

