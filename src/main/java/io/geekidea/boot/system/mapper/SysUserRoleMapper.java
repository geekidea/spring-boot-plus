package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysUserRole;
import io.geekidea.boot.system.query.SysUserRoleQuery;
import io.geekidea.boot.system.vo.SysUserRoleInfoVo;
import io.geekidea.boot.system.vo.SysUserRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色关系表 Mapper 接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 用户角色关系表详情
     *
     * @param id
     * @return
     */
    SysUserRoleInfoVo getSysUserRoleById(Long id);

    /**
     * 用户角色关系表分页列表
     *
     * @param sysUserRoleQuery
     * @return
     */
    List<SysUserRoleVo> getSysUserRoleList(SysUserRoleQuery sysUserRoleQuery);

}
