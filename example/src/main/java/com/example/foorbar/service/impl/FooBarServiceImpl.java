/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.foorbar.service.impl;

import com.example.foorbar.entity.FooBar;
import com.example.foorbar.mapper.FooBarMapper;
import com.example.foorbar.service.FooBarService;
import com.example.foorbar.param.FooBarPageParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.core.pagination.PageUtil;

/**
 * FooBar 服务实现类
 *
 * @author geekidea
 * @since 2020-03-17
 */
@Slf4j
@Service
public class FooBarServiceImpl extends BaseServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveFooBar(FooBar fooBar) throws Exception {
        return super.save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBar fooBar) throws Exception {
        return super.updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<FooBar> getFooBarPageList(FooBarPageParam fooBarPageParam) throws Exception {
        Page page = PageUtil.getPage(fooBarPageParam, OrderItem.desc(getLambdaColumn(FooBar::getCreateTime)));
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<FooBar>();
        IPage<FooBar> iPage = fooBarMapper.selectPage(page, wrapper);
        return new Paging<FooBar>(iPage);
    }

}
