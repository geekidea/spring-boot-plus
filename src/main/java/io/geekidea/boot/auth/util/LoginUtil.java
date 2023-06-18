package io.geekidea.boot.auth.util;

import io.geekidea.boot.auth.constant.LoginConstant;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.vo.LoginRedisVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.config.properties.LoginProperties;
import io.geekidea.boot.framework.exception.BusinessException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Component
@EnableConfigurationProperties(LoginProperties.class)
public class LoginUtil {

    private static LoginRedisService loginRedisService;

    public LoginUtil(LoginRedisService loginRedisService) {
        LoginUtil.loginRedisService = loginRedisService;
    }

    /**
     * 获取登录Redis用户信息
     *
     * @return
     */
    public static LoginRedisVo getLoginRedisVo() throws Exception {
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            return null;
        }
        LoginRedisVo loginRedisVo = loginRedisService.getLoginRedisVo(token);
        return loginRedisVo;
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
        Long userId = loginRedisService.getLoginUserId(token);
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
    public static List<Long> getRoleIds() throws Exception {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getRoleIds();
        }
        return null;
    }

    /**
     * 获取登录部门ID
     *
     * @return
     */
    public static Long getDeptId() throws Exception {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getDeptId();
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
        List<Long> roleIds = getRoleIds();
        boolean isAdmin = false;
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                if (LoginConstant.ADMIN_ROLE_ID_LIST.contains(roleId)) {
                    isAdmin = true;
                    break;
                }
            }
        }
        return isAdmin;
    }

    /**
     * 断言是否是管理员
     */
    public static void assertAdmin() throws Exception {
        boolean admin = isAdmin();
        if (!admin) {
            throw new BusinessException("不是管理员，无权限");
        }
    }

}
