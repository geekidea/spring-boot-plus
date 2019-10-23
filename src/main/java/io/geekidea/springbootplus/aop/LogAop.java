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

import io.geekidea.springbootplus.core.aop.AbstractLogAop;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;

/**
 * <p>
 * Controller Aop
 * 获取响应结果信息
 * </p>
 *
 * @author geekidea
 * @date 2019-10-23
 */
@Data
@Slf4j
@Aspect
public class LogAop extends AbstractLogAop {

    /**
     * 切点
     */
    private static final String POINTCUT = "execution(public * io.geekidea.springbootplus..*.controller..*.*(..))";

    @Around(POINTCUT)
    @Override
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("LogAop doAround...");
        return super.handle(joinPoint);
    }

    @Override
    protected void handleRequestInfo(Map<String, Object> map) {
        log.debug("处理请求参数信息");
        super.handleRequestInfo(map);
    }

    @Override
    protected void handleResponseInfo(Object result) {
        log.debug("处理响应结果信息");
        super.handleResponseInfo(result);
    }

}
