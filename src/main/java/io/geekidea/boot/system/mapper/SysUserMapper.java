package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.auth.vo.LoginUserVo;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.vo.SysUserInfoVo;
import io.geekidea.boot.system.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户 Mapper 接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 系统用户详情
     *
     * @param id
     * @return
     */
    SysUserInfoVo getSysUserById(Long id);

    /**
     * 系统用户分页列表
     *
     * @param sysUserQuery
     * @return
     */
    List<SysUserVo> getSysUserList(SysUserQuery sysUserQuery);

    /**
     * 根据用户名获取登录用户对象
     *
     * @param username
     * @return
     */
    LoginUserVo getLoginVoByUsername(String username);

}
