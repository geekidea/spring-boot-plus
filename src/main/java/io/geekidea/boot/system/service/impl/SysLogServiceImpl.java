package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.entity.SysLog;
import io.geekidea.boot.system.mapper.SysLogMapper;
import io.geekidea.boot.system.query.SysLogQuery;
import io.geekidea.boot.system.service.SysLogService;
import io.geekidea.boot.system.vo.SysLogInfoVo;
import io.geekidea.boot.system.vo.SysLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统日志 服务实现类
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Slf4j
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLogInfoVo getSysLogById(Long id) throws Exception {
        return sysLogMapper.getSysLogById(id);
    }

    @Override
    public Paging<SysLogVo> getSysLogList(SysLogQuery sysLogQuery) throws Exception {
        handlePage(sysLogQuery, OrderByItem.desc("id"));
        List<SysLogVo> list = sysLogMapper.getSysLogList(sysLogQuery);
        Paging<SysLogVo> paging = new Paging<>(list);
        return paging;
    }

}
