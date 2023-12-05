package io.geekidea.boot.auth.service.impl;

import io.geekidea.boot.auth.dto.LoginDto;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.service.LoginService;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.framework.exception.LoginException;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysMenuMapper;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.util.PasswordUtil;
import io.geekidea.boot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private LoginRedisService loginRedisService;


    @Override
    public LoginTokenVo login(LoginDto loginDto) throws Exception {
        String username = loginDto.getUsername();
        SysUser sysUser = sysUserMapper.getSysUserByUsername(username);
        if (sysUser == null) {
            throw new LoginException("用户信息不存在");
        }
        // 校验密码
        String dbPassword = sysUser.getPassword();
        String dbSalt = sysUser.getSalt();
        String password = loginDto.getPassword();
        String encryptPassword = PasswordUtil.encrypt(password, dbSalt);
        if (!encryptPassword.equals(dbPassword)) {
            throw new LoginException("账号密码错误");
        }
        // 校验用户状态
        Boolean status = sysUser.getStatus();
        if (status == false) {
            throw new LoginException("用户已禁用");
        }
        // 查询用户角色
        Long roleId = sysUser.getRoleId();
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        if (sysRole == null) {
            throw new LoginException("该用户不存在可用的角色");
        }
        // 设置登录用户对象
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(sysUser, loginVo);
        Long userId = sysUser.getId();
        loginVo.setUserId(userId);
        loginVo.setLoginTime(new Date());
        loginVo.setAdmin(sysUser.getIsAdmin());
        loginVo.setRoleCode(sysRole.getCode());
        loginVo.setRoleName(sysRole.getName());
        // 系统类型 1：管理后台，2：用户端
        loginVo.setSystemType(SystemType.ADMIN.getCode());
        // 获取登录用户的权限编码
        List<String> permissions = sysMenuMapper.getPermissionCodesByUserId(userId);
        // 生成token
        String token = TokenUtil.generateAdminToken(userId);
        // 保存登录信息到redis中
        loginRedisService.setLoginRedisVo(token, loginVo, permissions);
        // 返回token
        LoginTokenVo loginTokenVo = new LoginTokenVo();
        loginTokenVo.setToken(token);
        return loginTokenVo;
    }

    @Override
    public LoginVo getLoginUserInfo() throws Exception {
        return LoginUtil.getLoginVo();
    }

    @Override
    public void logout() throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        // 删除缓存
        loginRedisService.deleteLoginRedisVo(token);
    }
}
