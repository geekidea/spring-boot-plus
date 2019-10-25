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

package io.geekidea.springbootplus.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.enums.StateEnum;
import io.geekidea.springbootplus.system.entity.SysDepartment;
import io.geekidea.springbootplus.system.mapper.SysDepartmentMapper;
import io.geekidea.springbootplus.system.param.SysDepartmentQueryParam;
import io.geekidea.springbootplus.system.service.SysDepartmentService;
import io.geekidea.springbootplus.system.vo.SysDepartmentQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


/**
 * <pre>
 * 部门 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysDepartment(SysDepartment sysDepartment) throws Exception {
        return super.save(sysDepartment);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDepartment(SysDepartment sysDepartment) throws Exception {
        return super.updateById(sysDepartment);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDepartment(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysDepartmentQueryVo getSysDepartmentById(Serializable id) throws Exception {
        return sysDepartmentMapper.getSysDepartmentById(id);
    }

    @Override
    public Paging<SysDepartmentQueryVo> getSysDepartmentPageList(SysDepartmentQueryParam sysDepartmentQueryParam) throws Exception {
        Page page = setPageParam(sysDepartmentQueryParam, OrderItem.desc("create_time"));
        IPage<SysDepartmentQueryVo> iPage = sysDepartmentMapper.getSysDepartmentPageList(page, sysDepartmentQueryParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isEnableSysDepartment(Long id) throws Exception {
        SysDepartment sysDepartment = new SysDepartment()
                .setId(id)
                .setState(StateEnum.ENABLE.ordinal());
        int count = sysDepartmentMapper.selectCount(new QueryWrapper<>(sysDepartment));
        return count > 0;
    }

}
