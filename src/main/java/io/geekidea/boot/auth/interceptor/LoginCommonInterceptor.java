package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.annotation.Login;
import io.geekidea.boot.auth.util.LoginAppUtil;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginRedisVo;
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
        // 排除不需要登录验证的路径 路径配置 注解
        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (login == null) {
            login = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Login.class);
            if (login == null) {
                return true;
            }
        }
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken(token);
        if (SystemType.ADMIN == systemType) {
            // 获取管理后台登录用户信息
            LoginRedisVo loginRedisVo = LoginUtil.getLoginRedisVo();
            if (loginRedisVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        } else if (SystemType.APP == systemType) {
            LoginAppVo loginVo = LoginAppUtil.getLoginVo();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        }
        return true;
    }

}
