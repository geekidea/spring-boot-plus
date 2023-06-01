package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysRoleMenu;
import io.geekidea.boot.system.query.SysRoleMenuQuery;
import io.geekidea.boot.system.vo.SysRoleMenuInfoVo;
import io.geekidea.boot.system.vo.SysRoleMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色菜单关系表 Mapper 接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 角色菜单关系表详情
     *
     * @param id
     * @return
     */
    SysRoleMenuInfoVo getSysRoleMenuById(Long id);

    /**
     * 角色菜单关系表分页列表
     *
     * @param sysRoleMenuQuery
     * @return
     */
    List<SysRoleMenuVo> getSysRoleMenuList(SysRoleMenuQuery sysRoleMenuQuery);

}
