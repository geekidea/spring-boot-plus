package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.SysRoleMenuAddDto;
import io.geekidea.boot.system.dto.SysRoleMenuUpdateDto;
import io.geekidea.boot.system.entity.SysRoleMenu;
import io.geekidea.boot.system.query.SysRoleMenuQuery;
import io.geekidea.boot.system.vo.SysRoleMenuInfoVo;
import io.geekidea.boot.system.vo.SysRoleMenuVo;

/**
 * 角色菜单关系表 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

    /**
     * 添加角色菜单关系表
     *
     * @param sysRoleMenuAddDto
     * @return
     * @throws Exception
     */
    boolean addSysRoleMenu(SysRoleMenuAddDto sysRoleMenuAddDto) throws Exception;

    /**
     * 修改角色菜单关系表
     *
     * @param sysRoleMenuUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysRoleMenu(SysRoleMenuUpdateDto sysRoleMenuUpdateDto) throws Exception;

    /**
     * 删除角色菜单关系表
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRoleMenu(Long id) throws Exception;

    /**
     * 角色菜单关系表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleMenuInfoVo getSysRoleMenuById(Long id) throws Exception;

    /**
     * 角色菜单关系表分页列表
     *
     * @param sysRoleMenuQuery
     * @return
     * @throws Exception
     */
    Paging<SysRoleMenuVo> getSysRoleMenuList(SysRoleMenuQuery sysRoleMenuQuery) throws Exception;

}
