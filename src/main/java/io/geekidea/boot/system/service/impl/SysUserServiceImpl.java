package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysUserAddDto;
import io.geekidea.boot.system.dto.SysUserUpdateDto;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserInfoVo;
import io.geekidea.boot.system.vo.SysUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统用户 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysUser(SysUserAddDto sysUserAddDto) throws Exception {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserAddDto, sysUser);
        return save(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(SysUserUpdateDto sysUserUpdateDto) throws Exception {
        Long id = sysUserUpdateDto.getId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        BeanUtils.copyProperties(sysUserUpdateDto, sysUser);
        sysUser.setUpdateTime(new Date());
        return updateById(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysUserInfoVo getSysUserById(Long id) throws Exception {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public Paging<SysUserVo> getSysUserList(SysUserQuery sysUserQuery) throws Exception {
        handlePage(sysUserQuery, OrderByItem.desc("id"));
        List<SysUserVo> list = sysUserMapper.getSysUserList(sysUserQuery);
        Paging<SysUserVo> paging = new Paging<>(list);
        return paging;
    }

}
