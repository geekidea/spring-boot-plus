package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysLogAddDto;
import io.geekidea.boot.system.dto.SysLogUpdateDto;
import io.geekidea.boot.system.entity.SysLog;
import io.geekidea.boot.system.mapper.SysLogMapper;
import io.geekidea.boot.system.query.SysLogQuery;
import io.geekidea.boot.system.service.SysLogService;
import io.geekidea.boot.system.vo.SysLogInfoVo;
import io.geekidea.boot.system.vo.SysLogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统日志 服务实现类
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysLog(SysLogAddDto sysLogAddDto) throws Exception {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(sysLogAddDto, sysLog);
        return save(sysLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysLog(SysLogUpdateDto sysLogUpdateDto) throws Exception {
        Long id = sysLogUpdateDto.getId();
        SysLog sysLog = getById(id);
        if (sysLog == null) {
            throw new BusinessException("系统日志不存在");
        }
        BeanUtils.copyProperties(sysLogUpdateDto, sysLog);
        sysLog.setUpdateTime(new Date());
        return updateById(sysLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysLog(Long id) throws Exception {
        return removeById(id);
    }

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
