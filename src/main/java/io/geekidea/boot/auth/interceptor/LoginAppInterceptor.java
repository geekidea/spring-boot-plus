package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.annotation.Vip;
import io.geekidea.boot.auth.cache.LoginAppCache;
import io.geekidea.boot.auth.util.LoginAppUtil;
import io.geekidea.boot.auth.vo.LoginAppVo;
import io.geekidea.boot.auth.vo.LoginRedisAppVo;
import io.geekidea.boot.framework.exception.AuthException;
import io.geekidea.boot.framework.exception.LoginTokenException;
import io.geekidea.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import io.geekidea.boot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
public class LoginAppInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 如果不存在@Login注解，则跳过
        boolean existLoginAnnotation = isExistLoginAnnotation(handlerMethod);
        if (!existLoginAnnotation) {
            return true;
        }
        // 移动端登录校验
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        // 获取登录用户信息
        LoginRedisAppVo loginRedisAppVo = LoginAppUtil.getLoginRedisVo(token);
        if (loginRedisAppVo == null) {
            throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
        }
        // 将APP移动端的登录信息保存到当前线程中
        LoginAppCache.set(loginRedisAppVo);
        // 获取登录信息判断
        LoginAppVo loginAppVo = loginRedisAppVo.getLoginVo();
        // 校验VIP等级
        Vip vip = handlerMethod.getMethodAnnotation(Vip.class);
        if (vip != null) {
            boolean isVip = loginAppVo.isVip();
            if (!isVip) {
                throw new AuthException("只有VIP才能操作");
            }
            // 判断VIP是否有对应的权限
            Integer vipLevel = loginAppVo.getVipLevel();
            boolean isOk = hasVipPermission(vip, vipLevel);
            if (!isOk) {
                throw new AuthException("当前VIP权限不足");
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginAppCache.remove();
    }

    /**
     * 是否有VIP权限
     *
     * @param vip
     * @param vipLevel
     * @return
     */
    private boolean hasVipPermission(Vip vip, Integer vipLevel) {
        if (vipLevel == null || vipLevel <= 0) {
            throw new AuthException("VIP等级错误");
        }
        int eq = vip.eq();
        int gt = vip.gt();
        int lt = vip.lt();
        int ge = vip.ge();
        int le = vip.le();
        int[] levels = vip.levels();
        if (eq != 0 && vipLevel.equals(eq)) {
            return true;
        }
        if (gt != 0 && vipLevel > gt) {
            return true;
        }
        if (lt != 0 && vipLevel < lt) {
            return true;
        }
        if (ge != 0 && vipLevel >= ge) {
            return true;
        }
        if (le != 0 && vipLevel <= le) {
            return true;
        }
        if (ArrayUtils.isNotEmpty(levels) && ArrayUtils.contains(levels, vipLevel)) {
            return true;
        }
        return false;
    }

}
