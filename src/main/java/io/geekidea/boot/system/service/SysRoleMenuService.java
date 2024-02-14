package io.geekidea.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.system.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色菜单关系表 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色ID删除角色权限关联关系
     *
     * @param roleId
     */
    void deleteSysRoleMenuByRoleId(Long roleId);

    /**
     * 根据菜单ID删除角色菜单关联关系
     *
     * @param menuIds
     */
    void deleteSysRoleMenuByMenuIds(List<Long> menuIds);

}
