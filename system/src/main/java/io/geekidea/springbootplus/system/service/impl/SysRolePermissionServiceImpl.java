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

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.system.entity.SysRolePermission;
import io.geekidea.springbootplus.system.enums.StateEnum;
import io.geekidea.springbootplus.system.mapper.SysRoleMapper;
import io.geekidea.springbootplus.system.mapper.SysRolePermissionMapper;
import io.geekidea.springbootplus.system.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRolePermission(Long roleId, List<Long> permissionIds) throws Exception {
        List<SysRolePermission> list = new ArrayList<>();
        permissionIds.forEach(permissionId -> {
            SysRolePermission sysRolePermission = new SysRolePermission()
                    .setRoleId(roleId)
                    .setPermissionId(permissionId)
                    .setState(StateEnum.ENABLE.getCode());
            list.add(sysRolePermission);
        });
        // 批量保存角色权限中间表
        return saveBatch(list, 20);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception {
        Wrapper wrapper = lambdaQuery()
                .select(SysRolePermission::getPermissionId)
                .eq(SysRolePermission::getRoleId,roleId)
                .eq(SysRolePermission::getState,StateEnum.ENABLE.getCode())
                .getWrapper();
        return sysRolePermissionMapper.selectObjs(wrapper);
    }

    @Override
    public List<Long> getThreeLevelPermissionIdsByRoleId(Long roleId) throws Exception {
        return sysRolePermissionMapper.getThreeLevelPermissionIdsByRoleId(roleId);
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
                    .setState(StateEnum.ENABLE.getCode());
            list.add(sysRolePermission);
        });
        return saveBatch(list, 20);
    }

    @Override
    public boolean deleteSysRolePermissionByRoleId(Long roleId) throws Exception {
        SysRolePermission sysRolePermission = new SysRolePermission()
                .setRoleId(roleId);
        return remove(new QueryWrapper<>(sysRolePermission));
    }

    @Override
    public Set<String> getPermissionCodesByRoleId(Long roleId) throws Exception {
        return sysRolePermissionMapper.getPermissionCodesByRoleId(roleId);
    }

    @Override
    public boolean isExistsByPermissionId(Long permissionId) throws Exception {
        // 判断角色权限表是否有关联存在，如果存在，则不能删除
        SysRolePermission sysRolePermission = new SysRolePermission()
                .setPermissionId(permissionId);
        return count(new QueryWrapper<SysRolePermission>(sysRolePermission)) > 0;
    }

    @Override
    public boolean hasPermission(Long roleId) throws Exception {
        SysRolePermission sysRolePermission = new SysRolePermission()
                .setRoleId(roleId);
        return count(new QueryWrapper<SysRolePermission>(sysRolePermission)) > 0;
    }

}
