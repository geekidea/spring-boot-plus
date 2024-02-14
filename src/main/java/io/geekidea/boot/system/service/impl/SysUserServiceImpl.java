package io.geekidea.boot.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.common.constant.CommonConstant;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.*;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserVo;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.util.PasswordUtil;
import io.geekidea.boot.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
    public boolean addSysUser(SysUserDto dto) {
        checkUsernameExists(dto.getUsername());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(dto, sysUser);
        // 密码加盐
        String salt = RandomStringUtils.randomNumeric(6);
        sysUser.setSalt(salt);
        String password = PasswordUtil.encrypt(sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        // 保存用户
        return save(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(SysUserDto dto) {
        Long id = dto.getId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        BeanUtils.copyProperties(dto, sysUser);
        return updateById(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) {
        // 删除用户
        return removeById(id);
    }

    @Override
    public SysUserVo getSysUserById(Long id) {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public Paging<SysUserVo> getSysUserPage(SysUserQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<SysUserVo> list = sysUserMapper.getSysUserPage(query);
        Paging<SysUserVo> paging = new Paging<>(list);
        return paging;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean resetSysUserPassword(SysUserResetPasswordDto sysUserResetPasswordDto) {
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
    public boolean updateProfile(SysUserUpdateProfileDto sysUserUpdateProfileDto) {
        Long id = LoginUtil.getUserId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SysUser::getNickname, sysUserUpdateProfileDto.getNickname());
        lambdaUpdateWrapper.set(SysUser::getPhone, sysUserUpdateProfileDto.getPhone());
        lambdaUpdateWrapper.set(SysUser::getEmail, sysUserUpdateProfileDto.getEmail());
        lambdaUpdateWrapper.set(SysUser::getHead, sysUserUpdateProfileDto.getHead());
        lambdaUpdateWrapper.eq(SysUser::getId, id);
        boolean flag = update(lambdaUpdateWrapper);
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePassword(SysUserUpdatePasswordDto sysUserUpdatePasswordDto) {
        Long id = LoginUtil.getUserId();
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
        // 新密码不能与旧密码一致
        String newPassword = PasswordUtil.encrypt(password, dbSalt);
        if (dbPassword.equals(newPassword)) {
            throw new BusinessException("新密码不能与旧密码一致");
        }
        return handleUpdatePassword(id, password);
    }

    @Override
    public void checkUsernameExists(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(username + "用户名已经存在");
        }
    }

    @Override
    public boolean importExcel(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("导入的Excel文件名称：" + originalFilename);
        String extension = FilenameUtils.getExtension(originalFilename).toLowerCase();
        if (!(extension.endsWith(CommonConstant.XLS) || extension.endsWith(CommonConstant.XLSX))) {
            throw new BusinessException("只能导入xls或xlsx格式的Excel文件");
        }
        InputStream inputStream = multipartFile.getInputStream();

        EasyExcel.read(inputStream, SysUserExcelDto.class, new PageReadListener<SysUserExcelDto>(dataList -> {
            for (SysUserExcelDto demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        }, 10)).sheet().doRead();


        return false;
    }

    /**
     * 修改密码并删除该用户当前的登录信息
     *
     * @param id
     * @param password
     * @return
     * @throws Exception
     */
    private boolean handleUpdatePassword(Long id, String password) {
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
