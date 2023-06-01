package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysUserRoleAddDto;
import io.geekidea.boot.system.dto.SysUserRoleUpdateDto;
import io.geekidea.boot.system.entity.SysUserRole;
import io.geekidea.boot.system.mapper.SysUserRoleMapper;
import io.geekidea.boot.system.query.SysUserRoleQuery;
import io.geekidea.boot.system.service.SysUserRoleService;
import io.geekidea.boot.system.vo.SysUserRoleInfoVo;
import io.geekidea.boot.system.vo.SysUserRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户角色关系表 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysUserRole(SysUserRoleAddDto sysUserRoleAddDto) throws Exception {
        SysUserRole sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(sysUserRoleAddDto, sysUserRole);
        return save(sysUserRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUserRole(SysUserRoleUpdateDto sysUserRoleUpdateDto) throws Exception {
        Long id = sysUserRoleUpdateDto.getId();
        SysUserRole sysUserRole = getById(id);
        if (sysUserRole == null) {
            throw new BusinessException("用户角色关系表不存在");
        }
        BeanUtils.copyProperties(sysUserRoleUpdateDto, sysUserRole);
        sysUserRole.setUpdateTime(new Date());
        return updateById(sysUserRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUserRole(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysUserRoleInfoVo getSysUserRoleById(Long id) throws Exception {
        return sysUserRoleMapper.getSysUserRoleById(id);
    }

    @Override
    public Paging<SysUserRoleVo> getSysUserRoleList(SysUserRoleQuery sysUserRoleQuery) throws Exception {
        handlePage(sysUserRoleQuery, OrderByItem.desc("id"));
        List<SysUserRoleVo> list = sysUserRoleMapper.getSysUserRoleList(sysUserRoleQuery);
        Paging<SysUserRoleVo> paging = new Paging<>(list);
        return paging;
    }

}
