package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.SysRole;
import io.geekidea.springbootplus.system.mapper.SysRoleMapper;
import io.geekidea.springbootplus.system.service.SysRoleService;
import io.geekidea.springbootplus.system.param.SysRoleQueryParam;
import io.geekidea.springbootplus.system.vo.SysRoleQueryVo;
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
 * 系统角色 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRole(SysRole sysRole) throws Exception {
        return super.save(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(SysRole sysRole) throws Exception {
        return super.updateById(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysRoleQueryVo getSysRoleById(Serializable id) throws Exception {
        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<SysRoleQueryVo> getSysRolePageList(SysRoleQueryParam sysRoleQueryParam) throws Exception {
        Page page = setPageParam(sysRoleQueryParam, OrderItem.desc("create_time"));
        IPage<SysRoleQueryVo> iPage = sysRoleMapper.getSysRolePageList(page, sysRoleQueryParam);
        return new Paging(iPage);
    }

}
