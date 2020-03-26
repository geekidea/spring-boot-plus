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

package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.framework.common.service.BaseService;
import io.geekidea.springbootplus.system.entity.SysRolePermission;
import org.apache.commons.collections4.SetUtils;

import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 角色权限关系 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {

    /**
     * 保存
     *
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    boolean saveSysRolePermission(Long roleId, List<Long> permissionIds) throws Exception;

    /**
     * 根据角色id获取权限id列表
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception;

    /**
     * 根据角色id获取该对应的所有三级权限ID
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getThreeLevelPermissionIdsByRoleId(Long roleId) throws Exception;

    /**
     * 批量保存角色权限关系
     *
     * @param roleId
     * @param addSet
     * @return
     * @throws Exception
     */
    boolean saveSysRolePermissionBatch(Long roleId, SetUtils.SetView addSet) throws Exception;

    /**
     * 根据角色id删除关联的权限关系
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    boolean deleteSysRolePermissionByRoleId(Long roleId) throws Exception;

    /**
     * 根据角色id获取可用的权限编码
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    Set<String> getPermissionCodesByRoleId(Long roleId) throws Exception;

    /**
     * 通过角色id判断在角色权限表中是否有数据存在
     *
     * @param permissionId
     * @return
     * @throws Exception
     */
    boolean isExistsByPermissionId(Long permissionId) throws Exception;

    /**
     * 角色下是否有权限
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    boolean hasPermission(Long roleId) throws Exception;

}
