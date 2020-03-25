/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.aop;

import io.geekidea.springbootplus.framework.log.aop.BaseLogAop;
import io.geekidea.springbootplus.framework.log.bean.OperationLogInfo;
import io.geekidea.springbootplus.framework.log.bean.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Controller Aop
 * 获取响应结果信息
 * </p>
 *
 * @author geekidea
 * @date 2019-10-23
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(value = {"spring-boot-plus.aop.log.enable"}, matchIfMissing = true)
public class LogAop extends BaseLogAop {

    /**
     * 切点
     */
    private static final String POINTCUT =
            "execution(public * io.geekidea.springbootplus..*.controller..*.*(..)) || " +
                    "execution(public * com.example..*.controller..*.*(..))";

    @Around(POINTCUT)
    @Override
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.handle(joinPoint);
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "exception")
    @Override
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        super.handleAfterThrowing(exception);
    }

    @Override
    protected void setRequestId(RequestInfo requestInfo) {
        super.handleRequestId(requestInfo);
    }

    @Override
    protected void getRequestInfo(RequestInfo requestInfo) {
        // 处理请求参数日志
        super.handleRequestInfo(requestInfo);
    }

    @Override
    protected void getResponseResult(Object result) {
        // 处理响应结果日志
        super.handleResponseResult(result);
    }

    @Override
    protected void finish(RequestInfo requestInfo, OperationLogInfo operationLogInfo, Object result, Exception exception) {
        // 异步保存操作日志
        super.saveSysOperationLog(requestInfo, operationLogInfo, result, exception);
        // 异步保存登录日志
        super.saveSysLoginLog(requestInfo, operationLogInfo, result, exception);
    }

}
