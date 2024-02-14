package io.geekidea.boot.util;

import io.geekidea.boot.auth.util.AppLoginUtil;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.vo.AppLoginVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.framework.query.DataRangeQuery;

/**
 * @author geekidea
 * @date 2023/12/1
 **/
public class DataRangeUtil {

    /**
     * 设置Admin登录用户信息到查询参数中
     *
     * @param dataRangeQuery
     */
    public static void handleAdminLogin(DataRangeQuery dataRangeQuery) {
        LoginVo loginVo = LoginUtil.getLoginVo();
        if (loginVo != null) {
            Long userId = loginVo.getUserId();
            dataRangeQuery.setLoginCommonUserId(userId);
            dataRangeQuery.setLoginAdmin(loginVo.isAdmin());
            dataRangeQuery.setLoginUserId(userId);
            dataRangeQuery.setLoginRoleId(loginVo.getRoleId());
            dataRangeQuery.setLoginRoleCode(loginVo.getRoleCode());
        }
    }

    /**
     * 设置App登录用户信息到查询参数中
     *
     * @param dataRangeQuery
     */
    public static void handleAppLogin(DataRangeQuery dataRangeQuery) {
        AppLoginVo appLoginVo = AppLoginUtil.getLoginVo();
        if (appLoginVo != null) {
            Long userId = appLoginVo.getUserId();
            dataRangeQuery.setLoginCommonUserId(userId);
            dataRangeQuery.setLoginAppUserId(userId);
            dataRangeQuery.setLoginAppUserRoleId(appLoginVo.getUserRoleId());
            dataRangeQuery.setLoginAppUserRoleCode(appLoginVo.getUserRoleCode());
        }
    }

    /**
     * 设置登录用户信息到查询参数中
     *
     * @param dataRangeQuery
     */
    public static void handleCommonLogin(DataRangeQuery dataRangeQuery) {
        // 判断token是那个端
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken();
        if (SystemType.ADMIN == systemType) {
            LoginVo loginVo = LoginUtil.getLoginVo();
            if (loginVo != null) {
                dataRangeQuery.setLoginCommonUserId(loginVo.getUserId());
            }
        } else if (SystemType.APP == systemType) {
            AppLoginVo appLoginVo = AppLoginUtil.getLoginVo();
            if (appLoginVo != null) {
                dataRangeQuery.setLoginCommonUserId(appLoginVo.getUserId());
            }
        }
    }

}
