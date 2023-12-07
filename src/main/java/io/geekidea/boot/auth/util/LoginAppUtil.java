package io.geekidea.boot.auth.util;

import io.geekidea.boot.auth.cache.LoginAppCache;
import io.geekidea.boot.auth.service.LoginRedisAppService;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginRedisAppVo;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Component
public class LoginAppUtil {

    private static LoginRedisAppService loginRedisAppService;

    public LoginAppUtil(LoginRedisAppService loginRedisAppService) {
        LoginAppUtil.loginRedisAppService = loginRedisAppService;
    }

    /**
     * 根据token从redis中获取登录用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static LoginRedisAppVo getLoginRedisVo(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        TokenUtil.checkAppToken(token);
        LoginRedisAppVo loginRedisAppVo = loginRedisAppService.getLoginRedisVo(token);
        return loginRedisAppVo;
    }

    /**
     * 从当前线程中获取登录用户信息
     *
     * @return
     */
    public static LoginRedisAppVo getLoginRedisVo() throws Exception {
        return LoginAppCache.get();
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public static LoginAppVo getLoginVo() throws Exception {
        LoginRedisAppVo loginRedisAppVo = getLoginRedisVo();
        if (loginRedisAppVo != null) {
            LoginAppVo loginAppVo = loginRedisAppVo.getLoginVo();
            return loginAppVo;
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
        LoginAppVo loginAppVo = getLoginVo();
        Long userId = loginAppVo.getUserId();
        return userId;
    }

    /**
     * 获取登录用户名
     *
     * @return
     */
    public static String getUsername() throws Exception {
        LoginAppVo loginAppVo = getLoginVo();
        if (loginAppVo != null) {
            return loginAppVo.getUsername();
        }
        return null;
    }

    /**
     * 获取登录VIP级别
     *
     * @return
     */
    public static Integer getVipLevel() throws Exception {
        LoginAppVo loginAppVo = getLoginVo();
        if (loginAppVo != null) {
            return loginAppVo.getVipLevel();
        }
        return null;
    }

    /**
     * 判断是否是会员
     *
     * @return
     * @throws Exception
     */
    public static boolean isVip() throws Exception {
        LoginAppVo loginAppVo = getLoginVo();
        boolean vip = loginAppVo.isVip();
        return vip;
    }

    /**
     * 检查是否是管理员
     */
    public static void checkVip() throws Exception {
        boolean vip = isVip();
        if (!vip) {
            throw new BusinessException("不是管理员，无权限");
        }
    }

}
