package io.geekidea.springbootplus.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.system.param.SysUserQueryParam;
import io.geekidea.springbootplus.system.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * SystemUser Mapper 接口
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysUserQueryVo getSysUserById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysUserQueryParam
     * @return
     */
    IPage<SysUserQueryVo> getSysUserPageList(@Param("page") Page page, @Param("param") SysUserQueryParam sysUserQueryParam);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    SysUser getSysUserByUsername(String username);

}
