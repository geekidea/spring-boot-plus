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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2019-10-25
 **/
@Data
@Slf4j
@Aspect
@Component
public class HelloAop {

    private static final String POINTCUT = "execution(public * io.geekidea.springbootplus..*.controller..*.*(..))";

    @Before(POINTCUT)
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("doBefore...");
    }

    @AfterReturning(value = POINTCUT, returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
        System.out.println("doAfterReturning...");
    }

    @AfterThrowing(value = POINTCUT, throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) throws Throwable {
        System.out.println("doAfterThrowing...");
    }

    @After(value = POINTCUT)
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("doAfter...");
    }

}
