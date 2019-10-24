package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.system.mapper.SysUserMapper;
import io.geekidea.springbootplus.system.service.SysUserService;
import io.geekidea.springbootplus.system.param.SysUserQueryParam;
import io.geekidea.springbootplus.system.vo.SysUserQueryVo;
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
 * 系统用户 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysUser(SysUser sysUser) throws Exception {
        return super.save(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(SysUser sysUser) throws Exception {
        return super.updateById(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysUserQueryVo getSysUserById(Serializable id) throws Exception {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public Paging<SysUserQueryVo> getSysUserPageList(SysUserQueryParam sysUserQueryParam) throws Exception {
        Page page = setPageParam(sysUserQueryParam, OrderItem.desc("create_time"));
        IPage<SysUserQueryVo> iPage = sysUserMapper.getSysUserPageList(page, sysUserQueryParam);
        return new Paging(iPage);
    }

}
