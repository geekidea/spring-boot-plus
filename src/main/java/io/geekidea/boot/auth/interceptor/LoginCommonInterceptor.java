package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.cache.LoginAppCache;
import io.geekidea.boot.auth.cache.LoginCache;
import io.geekidea.boot.auth.util.LoginAppUtil;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.framework.exception.LoginTokenException;
import io.geekidea.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import io.geekidea.boot.util.SystemTypeUtil;
import io.geekidea.boot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
public class LoginCommonInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        SystemType systemType = null;
        if (StringUtils.isNotBlank(token)) {
            systemType = SystemTypeUtil.getSystemTypeByToken(token);
            if (SystemType.ADMIN == systemType) {
                // 获取管理后台登录用户信息
                LoginVo loginVo = LoginUtil.getLoginVo(token);
                if (loginVo != null) {
                    // 将管理后台的登录信息保存到当前线程中
                    LoginCache.set(loginVo);
                }
            } else if (SystemType.APP == systemType) {
                LoginAppVo loginVo = LoginAppUtil.getLoginVo(token);
                if (loginVo != null) {
                    // 将APP移动端的登录信息保存到当前线程中
                    LoginAppCache.set(loginVo);
                }
            }
        }
        // 如果不存在@Login注解，则跳过
        boolean existLoginAnnotation = isExistLoginAnnotation(handlerMethod);
        if (!existLoginAnnotation) {
            return true;
        }
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        if (SystemType.ADMIN == systemType) {
            // 获取管理后台登录用户信息
            LoginVo loginVo = LoginCache.get();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        } else if (SystemType.APP == systemType) {
            LoginAppVo loginVo = LoginAppCache.get();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginCache.remove();
        LoginAppCache.remove();
    }
}
