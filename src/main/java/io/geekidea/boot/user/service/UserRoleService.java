package io.geekidea.boot.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.user.dto.UserRoleDto;
import io.geekidea.boot.user.entity.UserRole;
import io.geekidea.boot.user.query.UserRoleQuery;
import io.geekidea.boot.user.vo.UserRoleVo;

/**
 * 用户角色 服务接口
 *
 * @author geekidea
 * @since 2024-01-06
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 添加用户角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addUserRole(UserRoleDto dto);

    /**
     * 修改用户角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateUserRole(UserRoleDto dto);

    /**
     * 删除用户角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUserRole(Long id);

    /**
     * 用户角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserRoleVo getUserRoleById(Long id);

    /**
     * 用户角色分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<UserRoleVo> getUserRolePage(UserRoleQuery query);

}
