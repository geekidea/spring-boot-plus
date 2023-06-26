package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.util.PasswordUtil;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.framework.util.UUIDUtil;
import io.geekidea.boot.system.dto.*;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserRoleService;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserInfoVo;
import io.geekidea.boot.system.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private LoginRedisService loginRedisService;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean resetSysUserPassword(SysUserResetPasswordDto sysUserResetPasswordDto) throws Exception {
        Long userId = sysUserResetPasswordDto.getUserId();
        log.info("管理员重置用户密码：" + userId);
        SysUser sysUser = getById(userId);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        String password = sysUserResetPasswordDto.getPassword();
        return handleUpdatePassword(userId, password);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProfile(SysUserUpdateProfileDto sysUserUpdateProfileDto) throws Exception {
        Long id = sysUserUpdateProfileDto.getId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SysUser::getNickname, sysUserUpdateProfileDto.getNickname());
        lambdaUpdateWrapper.set(SysUser::getPhone, sysUserUpdateProfileDto.getPhone());
        lambdaUpdateWrapper.set(SysUser::getEmail, sysUserUpdateProfileDto.getEmail());
        lambdaUpdateWrapper.set(SysUser::getGender, sysUserUpdateProfileDto.getGender());
        lambdaUpdateWrapper.set(SysUser::getHead, sysUserUpdateProfileDto.getHead());
        lambdaUpdateWrapper.eq(SysUser::getId, id);
        boolean flag = update(lambdaUpdateWrapper);
        // TODO 更新缓存中的用户信息
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePassword(SysUserUpdatePasswordDto sysUserUpdatePasswordDto) throws Exception {
        Long id = sysUserUpdatePasswordDto.getId();
        log.info("用户修改密码：" + id);
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        // 验证旧密码
        String dbPassword = sysUser.getPassword();
        String dbSalt = sysUser.getSalt();
        String oldPassword = sysUserUpdatePasswordDto.getOldPassword();
        String encryptOldPassword = PasswordUtil.encrypt(oldPassword, dbSalt);
        if (!dbPassword.equals(encryptOldPassword)) {
            throw new BusinessException("旧密码错误");
        }
        // 验证两次密码是否一致
        String password = sysUserUpdatePasswordDto.getPassword();
        String confirmPassword = sysUserUpdatePasswordDto.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }
        return handleUpdatePassword(id, password);
    }

    /**
     * 修改密码并删除该用户当前的登录信息
     *
     * @param id
     * @param password
     * @return
     * @throws Exception
     */
    private boolean handleUpdatePassword(Long id, String password) throws Exception {
        // 生产新的盐值
        String newSalt = UUIDUtil.getUuid();
        String newPassword = PasswordUtil.encrypt(password, newSalt);
        // 修改密码
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SysUser::getPassword, newPassword);
        lambdaUpdateWrapper.set(SysUser::getSalt, newSalt);
        lambdaUpdateWrapper.set(SysUser::getUpdateTime, new Date());
        lambdaUpdateWrapper.eq(SysUser::getId, id);
        // 清除当前用户登录信息
        loginRedisService.deleteLoginInfoByUserId(id);
        return update(lambdaUpdateWrapper);
    }

}
