package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysDepartment;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.SysDepartmentQueryParam;
import io.geekidea.springbootplus.system.vo.SysDepartmentQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;

import java.io.Serializable;

/**
 * <pre>
 * 部门 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysDepartmentService extends BaseService<SysDepartment> {


    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysDepartmentQueryVo getSysDepartmentById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysDepartmentQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysDepartmentQueryVo> getSysDepartmentPageList(SysDepartmentQueryParam sysDepartmentQueryParam) throws Exception;

}
