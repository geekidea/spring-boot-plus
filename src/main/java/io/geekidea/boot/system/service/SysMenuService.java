package io.geekidea.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.system.dto.SysMenuDto;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.query.SysMenuQuery;
import io.geekidea.boot.system.vo.SysMenuTreeVo;
import io.geekidea.boot.system.vo.SysMenuVo;
import io.geekidea.boot.system.vo.SysNavMenuTreeVo;

import java.util.List;

/**
 * 系统菜单 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 添加系统菜单
     *
     * @param sysMenuDto
     * @return
     * @throws Exception
     */
    boolean addSysMenu(SysMenuDto sysMenuDto) throws Exception;

    /**
     * 修改系统菜单
     *
     * @param sysMenuDto
     * @return
     * @throws Exception
     */
    boolean updateSysMenu(SysMenuDto sysMenuDto) throws Exception;

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
    SysMenuVo getSysMenuById(Long id) throws Exception;

    /**
     * 获取所有的系统菜单树形列表
     *
     * @param sysMenuQuery
     * @return
     * @throws Exception
     */
    List<SysMenuTreeVo> getAllSysMenuTreeList(SysMenuQuery sysMenuQuery) throws Exception;

    /**
     * 获取启用的系统菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysMenuTreeVo> getSysMenuTreeList() throws Exception;

    /**
     * 获取当前登录用户导航菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysNavMenuTreeVo> getNavMenuTreeList() throws Exception;

    /**
     * 获取角色权限ID集合
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getMenuIdsByRoleId(Long roleId) throws Exception;

}
