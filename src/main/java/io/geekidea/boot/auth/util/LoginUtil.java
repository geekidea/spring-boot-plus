package io.geekidea.boot.auth.util;

import io.geekidea.boot.auth.cache.LoginCache;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.vo.LoginRedisVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Component
public class LoginUtil {

    private static LoginRedisService loginRedisService;

    public LoginUtil(LoginRedisService loginRedisService) {
        LoginUtil.loginRedisService = loginRedisService;
    }

    /**
     * 根据token从redis中获取登录用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static LoginRedisVo getLoginRedisVo(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        TokenUtil.checkAdminToken(token);
        LoginRedisVo loginRedisVo = loginRedisService.getLoginRedisVo(token);
        return loginRedisVo;
    }

    /**
     * 从当前线程中获取登录用户信息
     *
     * @return
     */
    public static LoginRedisVo getLoginRedisVo() throws Exception {
        return LoginCache.get();
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public static LoginVo getLoginVo() throws Exception {
        LoginRedisVo loginRedisVo = getLoginRedisVo();
        if (loginRedisVo != null) {
            LoginVo loginVo = loginRedisVo.getLoginVo();
            return loginVo;
        }
        return null;
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public static List<String> getPermissions() throws Exception {
        LoginRedisVo loginRedisVo = getLoginRedisVo();
        if (loginRedisVo != null) {
            return loginRedisVo.getPermissions();
        }
        return null;
    }

    /**
     * 获取登录用户ID
     *
     * @return
     */
    public static Long getUserId() throws Exception {
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            return null;
        }
        LoginVo loginVo = getLoginVo();
        Long userId = loginVo.getUserId();
        return userId;
    }

    /**
     * 获取登录用户名
     *
     * @return
     */
    public static String getUsername() throws Exception {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getUsername();
        }
        return null;
    }

    /**
     * 获取登录角色ID
     *
     * @return
     */
    public static Long getRoleId() throws Exception {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getRoleId();
        }
        return null;
    }

    /**
     * 判断是否是管理员
     *
     * @return
     * @throws Exception
     */
    public static boolean isAdmin() throws Exception {
        LoginVo loginVo = getLoginVo();
        boolean admin = loginVo.isAdmin();
        return admin;
    }

    /**
     * 判断不是管理员
     *
     * @return
     * @throws Exception
     */
    public static boolean isNotAdmin() throws Exception {
        return !isAdmin();
    }

    /**
     * 检查是否是管理员
     */
    public static void checkAdmin() throws Exception {
        boolean admin = isAdmin();
        if (!admin) {
            throw new BusinessException("不是管理员，无权限");
        }
    }

}
