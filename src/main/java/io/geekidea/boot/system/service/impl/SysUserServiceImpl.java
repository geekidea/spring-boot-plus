package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.SysUserDto;
import io.geekidea.boot.system.dto.SysUserResetPasswordDto;
import io.geekidea.boot.system.dto.SysUserUpdatePasswordDto;
import io.geekidea.boot.system.dto.SysUserUpdateProfileDto;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserVo;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.util.PasswordUtil;
import io.geekidea.boot.util.TokenUtil;
import io.geekidea.boot.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginRedisService LoginRedisService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysUser(SysUserDto sysUserDto) throws Exception {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto, sysUser);
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
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(SysUserDto sysUserDto) throws Exception {
        Long id = sysUserDto.getId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        BeanUtils.copyProperties(sysUserDto, sysUser);
        sysUser.setUpdateTime(new Date());
        boolean flag = updateById(sysUser);
        if (!flag) {
            throw new BusinessException("修改系统用户异常");
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
        return true;
    }

    @Override
    public SysUserVo getSysUserById(Long id) throws Exception {
        SysUserVo sysUserVo = sysUserMapper.getSysUserById(id);
        if (sysUserVo == null) {
            throw new BusinessException("用户信息不存在");
        }
        return sysUserVo;
    }

    @Override
    public Paging<SysUserVo> getSysUserPage(SysUserQuery sysUserQuery) throws Exception {
        PagingUtil.handlePage(sysUserQuery, OrderByItem.desc("id"));
        List<SysUserVo> list = sysUserMapper.getSysUserPage(sysUserQuery);
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
        LoginRedisService.deleteLoginInfoByToken(TokenUtil.getToken());
        return update(lambdaUpdateWrapper);
    }

}
