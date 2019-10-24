package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysRole;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.SysRoleQueryParam;
import io.geekidea.springbootplus.system.vo.SysRoleQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;

import java.io.Serializable;

/**
 * <pre>
 * 系统角色 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 保存
     *
     * @param sysRole
     * @return
     * @throws Exception
     */
    boolean saveSysRole(SysRole sysRole) throws Exception;

    /**
     * 修改
     *
     * @param sysRole
     * @return
     * @throws Exception
     */
    boolean updateSysRole(SysRole sysRole) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRole(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleQueryVo getSysRoleById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysRoleQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysRoleQueryVo> getSysRolePageList(SysRoleQueryParam sysRoleQueryParam) throws Exception;

}
