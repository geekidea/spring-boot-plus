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

package io.geekidea.springbootplus.framework.log.annotation;

import io.geekidea.springbootplus.framework.log.enums.OperationLogType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 忽略操作日志记录注解
 * 在controller上标注该方法后，将不会记录操作日志
 * 可以标注在类和方法上，如果标记在类上，则会忽略controller中的所有方法
 *
 * @author geekidea
 * @date 2020/3/19
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogIgnore {

}
