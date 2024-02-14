package io.geekidea.boot.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.user.entity.UserRole;
import io.geekidea.boot.user.query.UserRoleQuery;
import io.geekidea.boot.user.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色 Mapper接口
 *
 * @author geekidea
 * @since 2024-01-06
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 用户角色详情
     *
     * @param id
     * @return
     */
    UserRoleVo getUserRoleById(Long id);

    /**
     * 用户角色分页列表
     *
     * @param query
     * @return
     */
    List<UserRoleVo> getUserRolePage(UserRoleQuery query);

}
