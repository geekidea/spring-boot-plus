package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.geekidea.boot.auth.util.PasswordUtil;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.ResetSysUserPasswordDto;
import io.geekidea.boot.system.dto.SysUserAddDto;
import io.geekidea.boot.system.dto.SysUserUpdateDto;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserRoleService;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserInfoVo;
import io.geekidea.boot.system.vo.SysUserVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysUser(SysUserAddDto sysUserAddDto) throws Exception {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserAddDto, sysUser);
        // 密码加盐
        String salt = RandomStringUtils.randomNumeric(6);
        sysUser.setSalt(salt);
        String password = PasswordUtil.encrypt(sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        // 保存用户
        boolean flag = save(sysUser);
        if (!flag) {
            throw new BusinessException("保存系统用户异常");
        }
        // 保存后的用户ID
        Long userId = sysUser.getId();
        // 角色ID集合
        List<Long> roleIds = sysUserAddDto.getRoleIds();
        // 保存用户角色关系
        boolean batchFlag = sysUserRoleService.batchSaveSysUserRole(userId, roleIds);
        if (!batchFlag) {
            throw new BusinessException("保存角色关系异常");
        }
        return true;
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
        boolean flag = updateById(sysUser);
        if (!flag) {
            throw new BusinessException("修改系统用户异常");
        }
        // 用户ID
        Long userId = sysUser.getId();
        // 先删除之前的用户角色对应关系
        boolean deleteSysUserRoleFlag = sysUserRoleService.deleteSysUserRoleByUserId(userId);
        if (!deleteSysUserRoleFlag) {
            throw new BusinessException("删除系统用户角色关系异常");
        }
        // 角色ID集合
        List<Long> roleIds = sysUserUpdateDto.getRoleIds();
        // 保存用户角色关系
        boolean batchFlag = sysUserRoleService.batchSaveSysUserRole(userId, roleIds);
        if (!batchFlag) {
            throw new BusinessException("更新角色关系异常");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        // 删除用户
        boolean flag = removeById(id);
        if (!flag) {
            throw new BusinessException("删除系统用户异常");
        }
        // 删除用户角色关系
        boolean deleteSysUserRoleFlag = sysUserRoleService.deleteSysUserRoleByUserId(id);
        if (!deleteSysUserRoleFlag) {
            throw new BusinessException("删除系统用户角色关系异常");
        }
        return true;
    }

    @Override
    public SysUserInfoVo getSysUserById(Long id) throws Exception {
        SysUserInfoVo sysUserInfoVo = sysUserMapper.getSysUserById(id);
        if (sysUserInfoVo == null) {
            throw new BusinessException("用户信息不存在");
        }
        // 根据用户ID获取角色列表
        List<SysRole> sysRoles = sysRoleMapper.getSysRolesByUserId(id);
        List<Long> roleIds = new ArrayList<>();
        List<String> roleNames = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            for (SysRole sysRole : sysRoles) {
                roleIds.add(sysRole.getId());
                roleNames.add(sysRole.getName());
            }
            sysUserInfoVo.setRoleIds(roleIds);
            sysUserInfoVo.setRoleNames(roleNames);
            sysUserInfoVo.setSysRoles(sysRoles);
        }
        return sysUserInfoVo;
    }

    @Override
    public Paging<SysUserVo> getSysUserList(SysUserQuery sysUserQuery) throws Exception {
        handlePage(sysUserQuery, OrderByItem.desc("id"));
        List<SysUserVo> list = sysUserMapper.getSysUserList(sysUserQuery);
        Paging<SysUserVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public boolean resetSysUserPassword(ResetSysUserPasswordDto resetSysUserPasswordDto) throws Exception {
        Long userId = resetSysUserPasswordDto.getUserId();
        SysUser sysUser = getById(userId);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        String salt = sysUser.getSalt();
        String password = resetSysUserPasswordDto.getPassword();
        String newPassword = PasswordUtil.encrypt(password, salt);
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SysUser::getPassword, newPassword);
        lambdaUpdateWrapper.set(SysUser::getUpdateTime, new Date());
        lambdaUpdateWrapper.eq(SysUser::getId, userId);
        return update(lambdaUpdateWrapper);
    }

}
