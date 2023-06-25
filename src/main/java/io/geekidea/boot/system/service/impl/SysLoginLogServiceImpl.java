package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysLoginLogAddDto;
import io.geekidea.boot.system.dto.SysLoginLogUpdateDto;
import io.geekidea.boot.system.entity.SysLoginLog;
import io.geekidea.boot.system.mapper.SysLoginLogMapper;
import io.geekidea.boot.system.query.SysLoginLogQuery;
import io.geekidea.boot.system.service.SysLoginLogService;
import io.geekidea.boot.system.vo.SysLoginLogInfoVo;
import io.geekidea.boot.system.vo.SysLoginLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统登录日志 服务实现类
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Slf4j
@Service
public class SysLoginLogServiceImpl extends BaseServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysLoginLog(SysLoginLogAddDto sysLoginLogAddDto) throws Exception {
        SysLoginLog sysLoginLog = new SysLoginLog();
        BeanUtils.copyProperties(sysLoginLogAddDto, sysLoginLog);
        return save(sysLoginLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysLoginLog(SysLoginLogUpdateDto sysLoginLogUpdateDto) throws Exception {
        Long id = sysLoginLogUpdateDto.getId();
        SysLoginLog sysLoginLog = getById(id);
        if (sysLoginLog == null) {
            throw new BusinessException("系统登录日志不存在");
        }
        BeanUtils.copyProperties(sysLoginLogUpdateDto, sysLoginLog);
        sysLoginLog.setUpdateTime(new Date());
        return updateById(sysLoginLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysLoginLog(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysLoginLogInfoVo getSysLoginLogById(Long id) throws Exception {
        return sysLoginLogMapper.getSysLoginLogById(id);
    }

    @Override
    public Paging<SysLoginLogVo> getSysLoginLogList(SysLoginLogQuery sysLoginLogQuery) throws Exception {
        handlePage(sysLoginLogQuery, OrderByItem.desc("id"));
        List<SysLoginLogVo> list = sysLoginLogMapper.getSysLoginLogList(sysLoginLogQuery);
        Paging<SysLoginLogVo> paging = new Paging<>(list);
        return paging;
    }

}
