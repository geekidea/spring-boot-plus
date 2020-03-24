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
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.param.SysPermissionPageParam;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
import io.geekidea.springbootplus.system.vo.SysPermissionTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 系统权限 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysPermissionService extends BaseService<SysPermission> {

    /**
     * 保存
     *
     * @param sysPermission
     * @return
     * @throws Exception
     */
    boolean saveSysPermission(SysPermission sysPermission) throws Exception;

    /**
     * 修改
     *
     * @param sysPermission
     * @return
     * @throws Exception
     */
    boolean updateSysPermission(SysPermission sysPermission) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysPermission(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysPermissionQueryVo getSysPermissionById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysPermissionPageParam
     * @return
     * @throws Exception
     */
    Paging<SysPermissionQueryVo> getSysPermissionPageList(SysPermissionPageParam sysPermissionPageParam) throws Exception;

    /**
     * 判断权限id是否存在
     *
     * @param permissionIds
     * @return
     * @throws Exception
     */
    boolean isExistsByPermissionIds(List<Long> permissionIds) throws Exception;

    /**
     * 获取所有菜单列表
     *
     * @return
     * @throws Exception
     */
    List<SysPermission> getAllMenuList() throws Exception;

    /**
     * 转换权限列表为树形菜单
     *
     * @param sysPermissions
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> convertSysPermissionTreeVoList(List<SysPermission> sysPermissions) throws Exception;

    /**
     * 获取获取菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> getAllMenuTree() throws Exception;

    /**
     * 根据用户id获取该用户所有权限编码
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<String> getPermissionCodesByUserId(Long userId) throws Exception;

    /**
     * 根据用户id获取菜单列表
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<SysPermission> getMenuListByUserId(Long userId) throws Exception;

    /**
     * 根据用户id获取菜单树形列表
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> getMenuTreeByUserId(Long userId) throws Exception;

    /**
     * 根据角色id获取该对应的所有三级权限ID
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception;


    /**
     * 获取所有导航菜单(一级/二级菜单)
     *
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> getNavMenuTree() throws Exception;
}
