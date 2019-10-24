package io.geekidea.springbootplus.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.param.SysPermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 系统权限 Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysPermissionQueryVo getSysPermissionById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysPermissionQueryParam
     * @return
     */
    IPage<SysPermissionQueryVo> getSysPermissionPageList(@Param("page") Page page, @Param("param") SysPermissionQueryParam sysPermissionQueryParam);

}
