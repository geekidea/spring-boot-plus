package io.geekidea.springbootplus.framework.log.service;

import io.geekidea.springbootplus.framework.common.service.BaseService;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.log.entity.SysLoginLog;
import io.geekidea.springbootplus.framework.log.param.SysLoginLogPageParam;

/**
 * 系统登录日志 服务类
 *
 * @author geekidea
 * @since 2020-03-24
 */
public interface SysLoginLogService extends BaseService<SysLoginLog> {

    /**
     * 保存
     *
     * @param sysLoginLog
     * @return
     * @throws Exception
     */
    boolean saveSysLoginLog(SysLoginLog sysLoginLog) throws Exception;

    /**
     * 修改
     *
     * @param sysLoginLog
     * @return
     * @throws Exception
     */
    boolean updateSysLoginLog(SysLoginLog sysLoginLog) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysLoginLog(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param sysLoginLogQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysLoginLog> getSysLoginLogPageList(SysLoginLogPageParam sysLoginLogPageParam) throws Exception;

}
