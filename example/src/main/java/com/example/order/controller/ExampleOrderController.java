/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.order.controller;

import com.example.order.entity.ExampleOrder;
import com.example.order.service.ExampleOrderService;
import lombok.extern.slf4j.Slf4j;
import com.example.order.param.ExampleOrderPageParam;
import io.geekidea.springbootplus.framework.common.controller.BaseController;
import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.common.param.IdParam;
import io.geekidea.springbootplus.framework.log.annotation.Module;
import io.geekidea.springbootplus.framework.log.annotation.OperationLog;
import io.geekidea.springbootplus.framework.log.enums.OperationLogType;
import io.geekidea.springbootplus.framework.core.validator.groups.Add;
import io.geekidea.springbootplus.framework.core.validator.groups.Update;
import org.springframework.validation.annotation.Validated;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单示例 控制器
 *
 * @author geekidea
 * @since 2020-03-27
 */
@Slf4j
@RestController
@RequestMapping("/exampleOrder")
@Module("order")
@Api(value = "ExampleOrderAPI", tags = {"ExampleOrder"})
public class ExampleOrderController extends BaseController {

    @Autowired
    private ExampleOrderService exampleOrderService;

    /**
     * 添加订单示例
     */
    @PostMapping("/add")
    @OperationLog(name = "添加订单示例", type = OperationLogType.ADD)
    @ApiOperation(value = "添加订单示例", response = ApiResult.class)
    public ApiResult<Boolean> addExampleOrder(@Validated(Add.class) @RequestBody ExampleOrder exampleOrder) throws Exception {
        boolean flag = exampleOrderService.saveExampleOrder(exampleOrder);
        return ApiResult.result(flag);
    }

    /**
     * 修改订单示例
     */
    @PostMapping("/update")
    @OperationLog(name = "修改订单示例", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改订单示例", response = ApiResult.class)
    public ApiResult<Boolean> updateExampleOrder(@Validated(Update.class) @RequestBody ExampleOrder exampleOrder) throws Exception {
        boolean flag = exampleOrderService.updateExampleOrder(exampleOrder);
        return ApiResult.result(flag);
    }

    /**
     * 删除订单示例
     */
    @PostMapping("/delete/{id}")
    @OperationLog(name = "删除订单示例", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除订单示例", response = ApiResult.class)
    public ApiResult<Boolean> deleteExampleOrder(@PathVariable("id") Long id) throws Exception {
        boolean flag = exampleOrderService.deleteExampleOrder(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取订单示例详情
     */
    @GetMapping("/info/{id}")
    @OperationLog(name = "订单示例详情", type = OperationLogType.INFO)
    @ApiOperation(value = "订单示例详情", response = ExampleOrder.class)
    public ApiResult<ExampleOrder> getExampleOrder(@PathVariable("id") Long id) throws Exception {
        ExampleOrder exampleOrder = exampleOrderService.getById(id);
        return ApiResult.ok(exampleOrder);
    }

    /**
     * 订单示例分页列表
     */
    @PostMapping("/getPageList")
    @OperationLog(name = "订单示例分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "订单示例分页列表", response = ExampleOrder.class)
    public ApiResult<Paging<ExampleOrder>> getExampleOrderPageList(@Validated @RequestBody ExampleOrderPageParam exampleOrderPageParam) throws Exception {
        Paging<ExampleOrder> paging = exampleOrderService.getExampleOrderPageList(exampleOrderPageParam);
        return ApiResult.ok(paging);
    }

}

