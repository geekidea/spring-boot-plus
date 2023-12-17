package io.geekidea.boot.auth.service.impl;

import io.geekidea.boot.auth.dto.AccountLoginAppDto;
import io.geekidea.boot.auth.dto.LoginAppDto;
import io.geekidea.boot.auth.service.LoginAppService;
import io.geekidea.boot.auth.service.LoginRedisAppService;
import io.geekidea.boot.auth.util.LoginAppUtil;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginTokenVo;
import io.geekidea.boot.common.constant.SysDictConstant;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.exception.LoginException;
import io.geekidea.boot.system.service.SysDictService;
import io.geekidea.boot.user.entity.User;
import io.geekidea.boot.user.service.UserService;
import io.geekidea.boot.util.IpRegionUtil;
import io.geekidea.boot.util.IpUtil;
import io.geekidea.boot.util.PasswordUtil;
import io.geekidea.boot.util.TokenUtil;
import io.geekidea.boot.util.api.WxMpApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
@Slf4j
@Service
public class LoginAppServiceImpl implements LoginAppService {

    @Autowired
    private UserService userService;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private LoginRedisAppService loginRedisAppService;

    @Override
    public LoginTokenVo login(LoginAppDto dto) throws Exception {
        String code = dto.getCode();
        // 获取微信openid
        String openid = WxMpApi.getOpenid(code);
        // 根据openid获取用户信息
        User user = userService.getUserByOpenid(openid);
        // 判断是否存在，不存在，则添加
        if (user == null) {
            // 注册
            user = new User();
            // 用户昵称：用户+8为随机数字
            user.setNickname("用户" + RandomStringUtils.randomNumeric(8));
            // 设置微信小程序openid
            user.setOpenid(openid);
            // 设置注册时间
            user.setRegisterTime(new Date());
            String requestIp = IpUtil.getRequestIp();
            String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
            // 设置注册IP
            user.setRegisterIp(requestIp);
            // 设置注册IP区域
            user.setRegisterIpArea(ipAreaDesc);
            boolean flag = userService.save(user);
            if (!flag) {
                throw new Exception("注册异常");
            }
            // 获取用户ID
            Long userId = user.getId();
            // 根据用户ID获取用户下信息
            user = userService.getById(userId);
        }
        return login(user);
    }

    @Override
    public LoginTokenVo accountLogin(AccountLoginAppDto dto) throws Exception {
        String username = dto.getUsername();
        User user = userService.getUserByUsername(username);
        // 校验密码
        String dbPassword = user.getPassword();
        String dbSalt = user.getSalt();
        String password = dto.getPassword();
        String encryptPassword = PasswordUtil.encrypt(password, dbSalt);
        if (!encryptPassword.equals(dbPassword)) {
            throw new BusinessException("账号密码错误");
        }
        return login(user);
    }

    @Override
    public LoginTokenVo login(User user) throws Exception {
        if (user == null) {
            throw new LoginException("用户信息不存在");
        }
        Long userId = user.getId();
        // 生成token
        String token = TokenUtil.generateAppToken(userId);
        // 最后一次登录时间
        Date lastLoginTime = new Date();
        // 刷新登录信息
        refreshLoginInfo(user, token, lastLoginTime);
        // 设置登录信息
        String requestIp = IpUtil.getRequestIp();
        String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
        // 设置最后登录时间
        user.setLastLoginTime(lastLoginTime);
        // 设置最后登录IP
        user.setLastLoginIp(requestIp);
        // 设置最后登录IP区域
        user.setLastLoginIpArea(ipAreaDesc);
        userService.updateById(user);

        // TODO 添加用户登录日志

        // 返回token
        LoginTokenVo loginTokenVo = new LoginTokenVo();
        loginTokenVo.setToken(token);
        return loginTokenVo;
    }

    @Override
    public LoginAppVo refreshLoginInfo(User user, String token, Date lastLoginTime) throws Exception {
        Long userId = user.getId();
        // 校验用户状态
        Boolean status = user.getStatus();
        if (status == false) {
            throw new LoginException("用户已禁用");
        }
        // 设置登录用户对象
        LoginAppVo loginAppVo = new LoginAppVo();
        BeanUtils.copyProperties(user, loginAppVo);
        loginAppVo.setUserId(userId);
        loginAppVo.setLastLoginTime(lastLoginTime);
        // 系统类型 1：管理后台，2：用户端
        loginAppVo.setSystemType(SystemType.APP.getCode());
        loginAppVo.setVip(user.getIsVip());
        Integer vipLevel = user.getVipLevel();
        if (vipLevel != null) {
            String vipName = sysDictService.getSysDictLabelByValue(SysDictConstant.VIP_LEVEL, vipLevel);
            loginAppVo.setVipName(vipName);
        }
        // 保存登录信息到redis中
        loginRedisAppService.setLoginVo(token, loginAppVo);
        return loginAppVo;
    }

    @Override
    public LoginAppVo getLoginUserInfo() throws Exception {
        LoginAppVo loginAppVo = LoginAppUtil.getLoginVo();
        if (loginAppVo == null) {
            throw new LoginException("请先登录");
        }
        Long userId = loginAppVo.getUserId();
        User user = userService.getById(userId);
        // 刷新用户登录信息
        String token = TokenUtil.getToken();
        Date lastLoginTime = loginAppVo.getLastLoginTime();
        loginAppVo = refreshLoginInfo(user, token, lastLoginTime);
        return loginAppVo;
    }

    @Override
    public void logout() throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        // 删除缓存
        loginRedisAppService.deleteLoginVo(token);
    }
}
