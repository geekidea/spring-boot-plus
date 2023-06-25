package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.*;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.vo.SysUserInfoVo;
import io.geekidea.boot.system.vo.SysUserVo;

/**
 * 系统用户 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 添加系统用户
     *
     * @param sysUserAddDto
     * @return
     * @throws Exception
     */
    boolean addSysUser(SysUserAddDto sysUserAddDto) throws Exception;

    /**
     * 修改系统用户
     *
     * @param sysUserUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysUser(SysUserUpdateDto sysUserUpdateDto) throws Exception;

    /**
     * 删除系统用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysUser(Long id) throws Exception;

    /**
     * 系统用户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysUserInfoVo getSysUserById(Long id) throws Exception;

    /**
     * 系统用户分页列表
     *
     * @param sysUserQuery
     * @return
     * @throws Exception
     */
    Paging<SysUserVo> getSysUserList(SysUserQuery sysUserQuery) throws Exception;

    /**
     * 重置系统用户密码
     *
     * @param sysUserResetPasswordDto
     * @return
     * @throws Exception
     */
    boolean resetSysUserPassword(SysUserResetPasswordDto sysUserResetPasswordDto) throws Exception;

    /**
     * 修改个人信息
     *
     * @param sysUserUpdateProfileDto
     * @return
     * @throws Exception
     */
    boolean updateProfile(SysUserUpdateProfileDto sysUserUpdateProfileDto) throws Exception;

    /**
     * 修改系统用户密码
     *
     * @param sysUserUpdatePasswordDto
     * @return
     * @throws Exception
     */
    boolean updatePassword(SysUserUpdatePasswordDto sysUserUpdatePasswordDto) throws Exception;

}
