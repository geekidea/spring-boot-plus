package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.mapper.SysPermissionMapper;
import io.geekidea.springbootplus.system.service.SysPermissionService;
import io.geekidea.springbootplus.system.param.SysPermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
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
 * <pre>
 * 系统权限 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysPermissionQueryVo getSysPermissionById(Serializable id) throws Exception {
        return sysPermissionMapper.getSysPermissionById(id);
    }

    @Override
    public Paging<SysPermissionQueryVo> getSysPermissionPageList(SysPermissionQueryParam sysPermissionQueryParam) throws Exception {
        Page page = setPageParam(sysPermissionQueryParam, OrderItem.desc("create_time"));
        IPage<SysPermissionQueryVo> iPage = sysPermissionMapper.getSysPermissionPageList(page, sysPermissionQueryParam);
        return new Paging(iPage);
    }

}
