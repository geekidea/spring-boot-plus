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

import io.geekidea.springbootplus.system.entity.SysRolePermission;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.SysRolePermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysRolePermissionQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;
import org.apache.commons.collections4.SetUtils;

import java.io.Serializable;
import java.util.List;

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
     * 修改
     *
     * @param sysRolePermission
     * @return
     * @throws Exception
     */
    boolean updateSysRolePermission(SysRolePermission sysRolePermission) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRolePermission(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRolePermissionQueryVo getSysRolePermissionById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysRolePermissionQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysRolePermissionQueryVo> getSysRolePermissionPageList(SysRolePermissionQueryParam sysRolePermissionQueryParam) throws Exception;

    /**
     * 根据角色id获取权限id列表
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception;

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
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRolePermissionByRoleId(Long id) throws Exception;

}
