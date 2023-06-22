package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.entity.SysLog;
import io.geekidea.boot.system.query.SysLogQuery;
import io.geekidea.boot.system.vo.SysLogInfoVo;
import io.geekidea.boot.system.vo.SysLogVo;

/**
 * 系统日志 服务接口
 *
 * @author geekidea
 * @since 2023-02-16
 */
public interface SysLogService extends BaseService<SysLog> {

    /**
     * 系统日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysLogInfoVo getSysLogById(Long id) throws Exception;

    /**
     * 系统日志分页列表
     *
     * @param sysLogQuery
     * @return
     * @throws Exception
     */
    Paging<SysLogVo> getSysLogList(SysLogQuery sysLogQuery) throws Exception;

}
