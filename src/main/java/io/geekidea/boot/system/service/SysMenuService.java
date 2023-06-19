package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.SysMenuAddDto;
import io.geekidea.boot.system.dto.SysMenuUpdateDto;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.query.SysMenuQuery;
import io.geekidea.boot.system.vo.SysMenuInfoVo;
import io.geekidea.boot.system.vo.SysMenuTreeVo;

import java.util.List;

/**
 * 系统菜单 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 添加系统菜单
     *
     * @param sysMenuAddDto
     * @return
     * @throws Exception
     */
    boolean addSysMenu(SysMenuAddDto sysMenuAddDto) throws Exception;

    /**
     * 修改系统菜单
     *
     * @param sysMenuUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysMenu(SysMenuUpdateDto sysMenuUpdateDto) throws Exception;

    /**
     * 删除系统菜单
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysMenu(Long id) throws Exception;

    /**
     * 系统菜单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysMenuInfoVo getSysMenuById(Long id) throws Exception;

    /**
     * 获取系统菜单树形列表
     *
     * @param sysMenuQuery
     * @return
     * @throws Exception
     */
    List<SysMenuTreeVo> getSysMenuTreeList(SysMenuQuery sysMenuQuery) throws Exception;

    /**
     * 获取启用的系统菜单树形列表
     *
     * @return
     */
    List<SysMenuTreeVo> getEnableSysMenuTreeList() throws Exception;

    /**
     * 获取当前登录用户导航菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysMenuTreeVo> getNavMenuTreeList() throws Exception;

}
