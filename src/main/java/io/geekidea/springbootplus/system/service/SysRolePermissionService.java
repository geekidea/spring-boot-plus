package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysRolePermission;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.SysRolePermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysRolePermissionQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;

import java.io.Serializable;

/**
 * <pre>
 * 角色权限关系 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {


    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRolePermissionQueryVo getSysRolePermissionById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysRolePermissionQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysRolePermissionQueryVo> getSysRolePermissionPageList(SysRolePermissionQueryParam sysRolePermissionQueryParam) throws Exception;

}
