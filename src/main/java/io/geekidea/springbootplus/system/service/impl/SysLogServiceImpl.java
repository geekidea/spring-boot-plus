package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.SysLog;
import io.geekidea.springbootplus.system.mapper.SysLogMapper;
import io.geekidea.springbootplus.system.service.SysLogService;
import io.geekidea.springbootplus.system.param.SysLogQueryParam;
import io.geekidea.springbootplus.system.vo.SysLogQueryVo;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;


/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLogQueryVo getSysLogById(Serializable id) throws Exception {
        return sysLogMapper.getSysLogById(id);
    }

    @Override
    public Paging<SysLogQueryVo> getSysLogPageList(SysLogQueryParam sysLogQueryParam) throws Exception {
        Page page = setPageParam(sysLogQueryParam, OrderItem.desc("create_time"));
        IPage<SysLogQueryVo> iPage = sysLogMapper.getSysLogPageList(page, sysLogQueryParam);
        return new Paging(iPage);
    }

}
