package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.web.param.SysUserQueryParam;
import io.geekidea.springbootplus.system.web.vo.SysUserQueryVo;
import io.geekidea.springbootplus.common.web.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * SystemUser 服务类
 * </p>
 *
 * @author geekidea
 * @since 2019-08-26
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    SysUserQueryVo getSysUserById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param sysUserQueryParam
     * @return
     */
    Paging<SysUserQueryVo> getSysUserPageList(SysUserQueryParam sysUserQueryParam) throws Exception;

}
