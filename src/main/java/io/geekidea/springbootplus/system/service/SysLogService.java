package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysLog;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.SysLogQueryParam;
import io.geekidea.springbootplus.system.vo.SysLogQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
public interface SysLogService extends BaseService<SysLog> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysLogQueryVo getSysLogById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysLogQueryParam
     * @return
     */
    Paging<SysLogQueryVo> getSysLogPageList(SysLogQueryParam sysLogQueryParam) throws Exception;

}
