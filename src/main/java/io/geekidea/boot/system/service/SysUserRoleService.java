package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.entity.SysUserRole;

import java.util.List;

/**
 * 用户角色关系表 服务接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 批量保存系统用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    boolean batchSaveSysUserRole(Long userId, List<Long> roleIds) throws Exception;

    /**
     * 根据用户ID删除用户角色关系
     *
     * @param userId
     * @return
     * @throws Exception
     */
    boolean deleteSysUserRoleByUserId(Long userId) throws Exception;


}
