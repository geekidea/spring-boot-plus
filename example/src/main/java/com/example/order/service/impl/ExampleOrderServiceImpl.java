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

package com.example.order.service.impl;

import com.example.order.entity.ExampleOrder;
import com.example.order.mapper.ExampleOrderMapper;
import com.example.order.service.ExampleOrderService;
import com.example.order.param.ExampleOrderPageParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.core.pagination.PageInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单示例 服务实现类
 *
 * @author geekidea
 * @since 2020-03-27
 */
@Slf4j
@Service
public class ExampleOrderServiceImpl extends BaseServiceImpl<ExampleOrderMapper, ExampleOrder> implements ExampleOrderService {

    @Autowired
    private ExampleOrderMapper exampleOrderMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveExampleOrder(ExampleOrder exampleOrder) throws Exception {
        return super.save(exampleOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateExampleOrder(ExampleOrder exampleOrder) throws Exception {
        return super.updateById(exampleOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteExampleOrder(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<ExampleOrder> getExampleOrderPageList(ExampleOrderPageParam exampleOrderPageParam) throws Exception {
        Page<ExampleOrder> page = new PageInfo<>(exampleOrderPageParam, OrderItem.desc(getLambdaColumn(ExampleOrder::getCreateTime)));
        LambdaQueryWrapper<ExampleOrder> wrapper = new LambdaQueryWrapper<>();
        String keyword = exampleOrderPageParam.getKeyword();
        String name = exampleOrderPageParam.getName();
        String orderNo = exampleOrderPageParam.getOrderNo();
        // keyword模糊查询
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like(ExampleOrder::getName, keyword).or().like(ExampleOrder::getOrderNo, keyword);
        }
        // name模糊查询
        if (StringUtils.isNotBlank(name)) {
            wrapper.like(ExampleOrder::getName, name);
        }
        // 订单号模糊查询
        if (StringUtils.isNotBlank(orderNo)) {
            wrapper.like(ExampleOrder::getOrderNo, orderNo);
        }
        IPage<ExampleOrder> iPage = exampleOrderMapper.selectPage(page, wrapper);
        return new Paging<ExampleOrder>(iPage);
    }

}
