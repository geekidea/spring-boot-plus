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
import io.geekidea.springbootplus.enums.StateEnum;
import io.geekidea.springbootplus.system.entity.SysRolePermission;
import io.geekidea.springbootplus.system.mapper.SysRolePermissionMapper;
import io.geekidea.springbootplus.system.service.SysRolePermissionService;
import io.geekidea.springbootplus.system.param.SysRolePermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysRolePermissionQueryVo;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * 角色权限关系 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-25
 */
@Slf4j
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRolePermission(Long roleId, List<Long> permissionIds) throws Exception {
        List<SysRolePermission> list = new ArrayList<>();
        permissionIds.forEach(permissionId -> {
            SysRolePermission sysRolePermission = new SysRolePermission()
                    .setRoleId(roleId)
                    .setPermissionId(permissionId)
                    .setState(StateEnum.ENABLE.ordinal());
            list.add(sysRolePermission);
        });
        // 批量保存角色权限中间表
        return saveBatch(list, 20);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRolePermission(SysRolePermission sysRolePermission) throws Exception {
        return super.updateById(sysRolePermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRolePermission(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysRolePermissionQueryVo getSysRolePermissionById(Serializable id) throws Exception {
        return sysRolePermissionMapper.getSysRolePermissionById(id);
    }

    @Override
    public Paging<SysRolePermissionQueryVo> getSysRolePermissionPageList(SysRolePermissionQueryParam sysRolePermissionQueryParam) throws Exception {
        Page page = setPageParam(sysRolePermissionQueryParam, OrderItem.desc("create_time"));
        IPage<SysRolePermissionQueryVo> iPage = sysRolePermissionMapper.getSysRolePermissionPageList(page, sysRolePermissionQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        return sysRolePermissionMapper.selectObjs(queryWrapper);
    }

    @Override
    public boolean saveSysRolePermissionBatch(Long roleId, SetUtils.SetView addSet) {
        List<SysRolePermission> list = new ArrayList<>();
        addSet.forEach(id -> {
            SysRolePermission sysRolePermission = new SysRolePermission();
            Long permissionId = (Long) id;
            sysRolePermission
                    .setRoleId(roleId)
                    .setPermissionId(permissionId)
                    .setState(StateEnum.ENABLE.ordinal());
            list.add(sysRolePermission);
        });
        return saveBatch(list, 20);
    }

    @Override
    public boolean deleteSysRolePermissionByRoleId(Long id) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", id);
        return remove(queryWrapper);
    }

}
