package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.SysLogAddDto;
import io.geekidea.boot.system.dto.SysLogUpdateDto;
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
     * 添加系统日志
     *
     * @param sysLogAddDto
     * @return
     * @throws Exception
     */
    boolean addSysLog(SysLogAddDto sysLogAddDto) throws Exception;

    /**
     * 修改系统日志
     *
     * @param sysLogUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysLog(SysLogUpdateDto sysLogUpdateDto) throws Exception;

    /**
     * 删除系统日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysLog(Long id) throws Exception;

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
