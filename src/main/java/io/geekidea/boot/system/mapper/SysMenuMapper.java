package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.query.SysMenuQuery;
import io.geekidea.boot.system.vo.SysMenuInfoVo;
import io.geekidea.boot.system.vo.SysMenuVo;
import org.apache.ibatis.annotations.Mapper;

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
    SysMenuInfoVo getSysMenuById(Long id);

    /**
     * 系统菜单分页列表
     *
     * @param sysMenuQuery
     * @return
     */
    List<SysMenuVo> getSysMenuList(SysMenuQuery sysMenuQuery);

    /**
     * 根据用户ID获取权限编码集合
     *
     * @param userId
     * @return
     */
    List<String> getPermissionCodesByUserId(Long userId);

}
