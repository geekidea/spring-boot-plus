package io.geekidea.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.entity.SysLog;
import io.geekidea.boot.system.query.SysLogQuery;
import io.geekidea.boot.system.vo.SysLogVo;

/**
 * 系统日志 服务接口
 *
 * @author geekidea
 * @since 2023-02-16
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 系统日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysLogVo getSysLogById(Long id) throws Exception;

    /**
     * 系统日志分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysLogVo> getSysLogPage(SysLogQuery query) throws Exception;

}
