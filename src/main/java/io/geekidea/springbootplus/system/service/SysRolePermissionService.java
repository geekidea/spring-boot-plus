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
     * 保存
     *
     * @param sysRolePermission
     * @return
     * @throws Exception
     */
    boolean saveSysRolePermission(SysRolePermission sysRolePermission) throws Exception;

    /**
     * 修改
     *
     * @param sysRolePermission
     * @return
     * @throws Exception
     */
    boolean updateSysRolePermission(SysRolePermission sysRolePermission) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRolePermission(Long id) throws Exception;

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
