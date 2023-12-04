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
import io.geekidea.boot.framework.exception.LoginException;
import io.geekidea.boot.system.service.SysDictService;
import io.geekidea.boot.user.entity.User;
import io.geekidea.boot.user.service.UserService;
import io.geekidea.boot.util.IpRegionUtil;
import io.geekidea.boot.util.IpUtil;
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
    public LoginTokenVo login(LoginAppDto loginAppDto) throws Exception {
        String code = loginAppDto.getCode();
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
        }
        return login(user);
    }

    @Override
    public LoginTokenVo accountLogin(AccountLoginAppDto loginAccountAppDto) throws Exception {
        String username = loginAccountAppDto.getUsername();
        User user = userService.getUserByUsername(username);
        return login(user);
    }

    @Override
    public LoginTokenVo login(User user) throws Exception {
        if (user == null) {
            throw new LoginException("用户信息不存在");
        }
        // 校验用户状态
        Boolean status = user.getStatus();
        if (status == false) {
            throw new LoginException("用户已禁用");
        }

        // 设置登录信息
        String requestIp = IpUtil.getRequestIp();
        String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
        // 设置最后登录时间
        user.setLastLoginTime(new Date());
        // 设置最后登录IP
        user.setLastLoginIp(requestIp);
        // 设置最后登录IP区域
        user.setLastLoginIpArea(ipAreaDesc);
        userService.updateById(user);
        // TODO 添加用户登录日志

        // 设置登录用户对象
        LoginAppVo loginAppVo = new LoginAppVo();
        BeanUtils.copyProperties(user, loginAppVo);
        Long userId = user.getId();
        loginAppVo.setUserId(userId);
        // 系统类型 1：管理后台，2：用户端
        loginAppVo.setSystemType(SystemType.APP.getCode());
        loginAppVo.setVip(user.getIsVip());
        Integer vipLevel = user.getVipLevel();
        if (vipLevel != null) {
            String vipName = sysDictService.getSysDictLabelByValue(SysDictConstant.VIP_LEVEL, vipLevel);
            loginAppVo.setVipName(vipName);
        }
        // 生成token
        String token = TokenUtil.generateAppToken(userId);
        // 保存登录信息到redis中
        loginRedisAppService.setLoginRedisVo(token, loginAppVo);
        // 返回token
        LoginTokenVo loginTokenVo = new LoginTokenVo();
        loginTokenVo.setToken(token);
        return loginTokenVo;
    }

    @Override
    public LoginAppVo getLoginUserInfo() throws Exception {
        return LoginAppUtil.getLoginVo();
    }

    @Override
    public void logout() throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        // 删除缓存
        loginRedisAppService.deleteLoginRedisVo(token);
    }
}
