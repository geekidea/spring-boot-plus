package io.geekidea.boot.auth.service.impl;

import io.geekidea.boot.auth.dto.LoginDto;
import io.geekidea.boot.auth.exception.LoginException;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.service.LoginService;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.util.PasswordUtil;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.auth.vo.LoginUserVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.mapper.SysDeptMapper;
import io.geekidea.boot.system.mapper.SysMenuMapper;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.vo.SysDeptInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private LoginRedisService loginRedisService;


    @Override
    public LoginTokenVo login(LoginDto loginDto) throws Exception {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        LoginUserVo loginUserVo = sysUserMapper.getLoginVoByUsername(username);
        if (loginUserVo == null) {
            throw new LoginException("用户信息不存在");
        }
        // 校验密码
        String dbPassword = loginUserVo.getPassword();
        String dbSalt = loginUserVo.getSalt();
        String encryptPassword = PasswordUtil.encrypt(password, dbSalt);
        if (!encryptPassword.equals(dbPassword)) {
            throw new LoginException("账号密码错误");
        }
        // 校验用户状态
        Boolean status = loginUserVo.getStatus();
        if (status == false) {
            throw new LoginException("用户已禁用");
        }
        // 查询用户角色
        Long userId = loginUserVo.getUserId();
        List<SysRole> roles = sysRoleMapper.getSysRolesByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            throw new LoginException("该用户不存在可用的角色");
        }
        List<Long> roleIds = new ArrayList<>();
        List<String> roleNames = new ArrayList<>();
        List<String> roleCodes = new ArrayList<>();
        for (SysRole sysRole : roles) {
            roleIds.add(sysRole.getId());
            roleNames.add(sysRole.getName());
            roleCodes.add(sysRole.getCode());
        }
        // 查询用户部门
        Long deptId = loginUserVo.getDeptId();
        SysDeptInfoVo sysDeptInfoVo = sysDeptMapper.getSysDeptById(deptId);
        if (sysDeptInfoVo != null) {
            loginUserVo.setDeptId(sysDeptInfoVo.getId());
            loginUserVo.setDeptName(sysDeptInfoVo.getName());
        }
        // 设置登录用户对象
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(loginUserVo, loginVo);
        loginVo.setLoginTime(new Date());
        // 设置登录角色信息
        loginVo.setRoleIds(roleIds);
        loginVo.setRoleNames(roleNames);
        loginVo.setRoleCodes(roleCodes);
        // 获取登录用户的权限编码
        List<String> permissions = sysMenuMapper.getPermissionCodesByUserId(userId);
        // 生成token
        String token = TokenUtil.generateToken();
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
