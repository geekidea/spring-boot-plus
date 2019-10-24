package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.SysRolePermission;
import io.geekidea.springbootplus.system.mapper.SysRolePermissionMapper;
import io.geekidea.springbootplus.system.service.SysRolePermissionService;
import io.geekidea.springbootplus.system.param.SysRolePermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysRolePermissionQueryVo;
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
 * 角色权限关系 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRolePermission(SysRolePermission sysRolePermission) throws Exception {
        return super.save(sysRolePermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRolePermission(SysRolePermission sysRolePermission) throws Exception {
        return super.updateById(sysRolePermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRolePermission(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysRolePermissionQueryVo getSysRolePermissionById(Serializable id) throws Exception {
        return sysRolePermissionMapper.getSysRolePermissionById(id);
    }

    @Override
    public Paging<SysRolePermissionQueryVo> getSysRolePermissionPageList(SysRolePermissionQueryParam sysRolePermissionQueryParam) throws Exception {
        Page page = setPageParam(sysRolePermissionQueryParam, OrderItem.desc("create_time"));
        IPage<SysRolePermissionQueryVo> iPage = sysRolePermissionMapper.getSysRolePermissionPageList(page, sysRolePermissionQueryParam);
        return new Paging(iPage);
    }

}
