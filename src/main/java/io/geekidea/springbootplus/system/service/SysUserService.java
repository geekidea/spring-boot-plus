package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.SysUserQueryParam;
import io.geekidea.springbootplus.system.vo.SysUserQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;

import java.io.Serializable;

/**
 * <pre>
 * 系统用户 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 保存系统用户
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    boolean saveSysUser(SysUser sysUser) throws Exception;

    /**
     * 修改系统用户
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    boolean updateSysUser(SysUser sysUser) throws Exception;

    /**
     * 删除系统用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysUser(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysUserQueryVo getSysUserById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysUserQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysUserQueryVo> getSysUserPageList(SysUserQueryParam sysUserQueryParam) throws Exception;

}
