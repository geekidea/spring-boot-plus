/**
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

package io.geekidea.springbootplus.config.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liujixiang
 * @date 2019/8/5
 */
@Data
@Accessors(chain = true)
public class SpringBootPlusInterceptorConfig implements Serializable {
    private static final long serialVersionUID = -2738042100246082469L;

    /**
     * JWT拦截器排除路径
     */
    private InterceptorConfig jwtConfig;

    /**
     * TOKEN超时拦截器排除路径
     */
    private InterceptorConfig tokenTimeoutConfig;

    /**
     * 权限拦截器排除路径
     */
    private InterceptorConfig permissionConfig;

    @Data
    public static class InterceptorConfig {

        /**
         * 排除路径
         */
        private String excludePath;

    }

}
