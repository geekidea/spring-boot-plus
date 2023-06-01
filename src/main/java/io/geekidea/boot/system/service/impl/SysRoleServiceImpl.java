package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysRoleAddDto;
import io.geekidea.boot.system.dto.SysRoleUpdateDto;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.query.SysRoleQuery;
import io.geekidea.boot.system.service.SysRoleService;
import io.geekidea.boot.system.vo.SysRoleInfoVo;
import io.geekidea.boot.system.vo.SysRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统角色 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysRole(SysRoleAddDto sysRoleAddDto) throws Exception {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleAddDto, sysRole);
        return save(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(SysRoleUpdateDto sysRoleUpdateDto) throws Exception {
        Long id = sysRoleUpdateDto.getId();
        SysRole sysRole = getById(id);
        if (sysRole == null) {
            throw new BusinessException("系统角色不存在");
        }
        BeanUtils.copyProperties(sysRoleUpdateDto, sysRole);
        sysRole.setUpdateTime(new Date());
        return updateById(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysRoleInfoVo getSysRoleById(Long id) throws Exception {
        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<SysRoleVo> getSysRoleList(SysRoleQuery sysRoleQuery) throws Exception {
        handlePage(sysRoleQuery, OrderByItem.desc("id"));
        List<SysRoleVo> list = sysRoleMapper.getSysRoleList(sysRoleQuery);
        Paging<SysRoleVo> paging = new Paging<>(list);
        return paging;
    }

}
