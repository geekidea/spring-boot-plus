package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.SysUserRoleAddDto;
import io.geekidea.boot.system.dto.SysUserRoleUpdateDto;
import io.geekidea.boot.system.entity.SysUserRole;
import io.geekidea.boot.system.query.SysUserRoleQuery;
import io.geekidea.boot.system.vo.SysUserRoleInfoVo;
import io.geekidea.boot.system.vo.SysUserRoleVo;

/**
 * 用户角色关系表 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 添加用户角色关系表
     *
     * @param sysUserRoleAddDto
     * @return
     * @throws Exception
     */
    boolean addSysUserRole(SysUserRoleAddDto sysUserRoleAddDto) throws Exception;

    /**
     * 修改用户角色关系表
     *
     * @param sysUserRoleUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysUserRole(SysUserRoleUpdateDto sysUserRoleUpdateDto) throws Exception;

    /**
     * 删除用户角色关系表
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysUserRole(Long id) throws Exception;

    /**
     * 用户角色关系表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysUserRoleInfoVo getSysUserRoleById(Long id) throws Exception;

    /**
     * 用户角色关系表分页列表
     *
     * @param sysUserRoleQuery
     * @return
     * @throws Exception
     */
    Paging<SysUserRoleVo> getSysUserRoleList(SysUserRoleQuery sysUserRoleQuery) throws Exception;

}
