package io.geekidea.boot.system.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.system.dto.SysLoginLogAddDto;
import io.geekidea.boot.system.dto.SysLoginLogUpdateDto;
import io.geekidea.boot.system.entity.SysLoginLog;
import io.geekidea.boot.system.query.SysLoginLogQuery;
import io.geekidea.boot.system.vo.SysLoginLogInfoVo;
import io.geekidea.boot.system.vo.SysLoginLogVo;

/**
 * 系统登录日志 服务接口
 *
 * @author geekidea
 * @since 2023-02-16
 */
public interface SysLoginLogService extends BaseService<SysLoginLog> {

    /**
     * 添加系统登录日志
     *
     * @param sysLoginLogAddDto
     * @return
     * @throws Exception
     */
    boolean addSysLoginLog(SysLoginLogAddDto sysLoginLogAddDto) throws Exception;

    /**
     * 修改系统登录日志
     *
     * @param sysLoginLogUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateSysLoginLog(SysLoginLogUpdateDto sysLoginLogUpdateDto) throws Exception;

    /**
     * 删除系统登录日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysLoginLog(Long id) throws Exception;

    /**
     * 系统登录日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysLoginLogInfoVo getSysLoginLogById(Long id) throws Exception;

    /**
     * 系统登录日志分页列表
     *
     * @param sysLoginLogQuery
     * @return
     * @throws Exception
     */
    Paging<SysLoginLogVo> getSysLoginLogList(SysLoginLogQuery sysLoginLogQuery) throws Exception;

}
