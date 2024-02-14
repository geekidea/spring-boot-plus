package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.query.SysMenuQuery;
import io.geekidea.boot.system.vo.SysMenuTreeVo;
import io.geekidea.boot.system.vo.SysMenuVo;
import io.geekidea.boot.system.vo.SysNavMenuTreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单 Mapper 接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 系统菜单详情
     *
     * @param id
     * @return
     */
    SysMenuVo getSysMenuById(Long id);

    /**
     * 根据用户ID获取权限编码集合
     *
     * @param userId
     * @return
     */
    List<String> getPermissionCodesByUserId(Long userId);

    /**
     * 获取系统菜单树形列表
     *
     * @param query
     * @return
     */
    List<SysMenuTreeVo> getSysMenuTreeList(SysMenuQuery query);

    /**
     * 管理员获取所有导航菜单树形列表
     *
     * @return
     */
    List<SysNavMenuTreeVo> getNavMenuTreeAllList();

    /**
     * 获取当前登录用户导航菜单树形列表
     *
     * @param userId
     * @return
     */
    List<SysNavMenuTreeVo> getNavMenuTreeList(Long userId);

    /**
     * 获取角色权限ID集合
     *
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 根据父菜单ID获取所有的子菜单ID集合
     *
     * @param parentIds
     * @return
     */
    List<Long> getChildrenMenuIds(@Param("parentIds") List<Long> parentIds);

}
