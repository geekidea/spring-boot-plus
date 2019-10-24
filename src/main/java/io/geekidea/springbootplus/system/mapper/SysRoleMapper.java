package io.geekidea.springbootplus.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.system.entity.SysRole;
import io.geekidea.springbootplus.system.param.SysRoleQueryParam;
import io.geekidea.springbootplus.system.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 系统角色 Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysRoleQueryVo getSysRoleById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysRoleQueryParam
     * @return
     */
    IPage<SysRoleQueryVo> getSysRolePageList(@Param("page") Page page, @Param("param") SysRoleQueryParam sysRoleQueryParam);

}
