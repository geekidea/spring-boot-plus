package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.RoleMenusDto;
import io.geekidea.boot.system.dto.SysRoleAddDto;
import io.geekidea.boot.system.dto.SysRoleUpdateDto;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.query.SysRoleQuery;
import io.geekidea.boot.system.vo.SysRoleInfoVo;
import io.geekidea.boot.system.vo.SysRoleVo;

import java.util.List;

/**
 * 系统角色 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 添加系统角色
     *
     * @param sysRoleAddDto
     * @return
     * @throws Exception
     */
    boolean addSysRole(SysRoleAddDto sysRoleAddDto) throws Exception;

    /**
     * 修改系统角色
     *
     * @param sysRoleUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysRole(SysRoleUpdateDto sysRoleUpdateDto) throws Exception;

    /**
     * 删除系统角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRole(Long id) throws Exception;

    /**
     * 系统角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleInfoVo getSysRoleById(Long id) throws Exception;

    /**
     * 系统角色分页列表
     *
     * @param sysRoleQuery
     * @return
     * @throws Exception
     */
    Paging<SysRoleVo> getSysRoleList(SysRoleQuery sysRoleQuery) throws Exception;

    /**
     * 获取所有角色列表
     *
     * @return
     * @throws Exception
     */
    List<SysRole> getSysRoleAllList() throws Exception;

    /**
     * 设置角色权限
     *
     * @param roleMenusDto
     * @return
     * @throws Exception
     */
    boolean setRoleMenus(RoleMenusDto roleMenusDto) throws Exception;

}
