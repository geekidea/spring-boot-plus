package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.query.SysRoleQuery;
import io.geekidea.boot.system.vo.SysRoleInfoVo;
import io.geekidea.boot.system.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色 Mapper 接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 系统角色详情
     *
     * @param id
     * @return
     */
    SysRoleInfoVo getSysRoleById(Long id);

    /**
     * 系统角色分页列表
     *
     * @param sysRoleQuery
     * @return
     */
    List<SysRoleVo> getSysRoleList(SysRoleQuery sysRoleQuery);

    /**
     * 根据用户ID获取所有角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getSysRolesByUserId(Long userId);

}
