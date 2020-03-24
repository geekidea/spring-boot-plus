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

package io.geekidea.springbootplus.framework.log.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 操作日志信息
 *
 * @author geekidea
 * @date 2020/3/19
 **/
@Data
@Accessors(chain = true)
public class OperationLogInfo {

    /**
     * 是否忽略
     */
    private boolean ignore;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 日志名称
     */
    private String name;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志备注
     */
    private String remark;

    /**
     * controller类名称
     */
    private String controllerClassName;

    /**
     * controller目标方法名称
     */
    private String controllerMethodName;

}
