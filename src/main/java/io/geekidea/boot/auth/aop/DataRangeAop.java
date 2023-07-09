package io.geekidea.boot.auth.aop;

import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.framework.constant.AspectConstant;
import io.geekidea.boot.framework.query.DataRangeQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2022/4/20
 **/
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "login.enable", havingValue = "true", matchIfMissing = true)
public class DataRangeAop {

    @Around(AspectConstant.COMMON_POINTCUT)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return joinPoint.proceed();
        }
        for (Object arg : args) {
            if (arg instanceof DataRangeQuery) {
                handleDataRangeQuery(arg);
                break;
            }
        }
        return joinPoint.proceed();
    }


    /**
     * 设置登录用户信息到查询参数中
     *
     * @param arg
     */
    private void handleDataRangeQuery(Object arg) throws Exception {
        LoginVo loginVo = LoginUtil.getLoginVo();
        if (loginVo != null) {
            DataRangeQuery dataRangeQuery = (DataRangeQuery) arg;
            dataRangeQuery.setLoginUserId(loginVo.getUserId());
            dataRangeQuery.setLoginRoleIds(loginVo.getRoleIds());
            dataRangeQuery.setLoginRoleCodes(loginVo.getRoleCodes());
            dataRangeQuery.setLoginDeptId(loginVo.getDeptId());
            dataRangeQuery.setAdmin(loginVo.isAdmin());
        }
    }


}
