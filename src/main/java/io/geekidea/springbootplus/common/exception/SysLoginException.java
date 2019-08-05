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

package io.geekidea.springbootplus.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统登录异常
 * @author geekidea
 * @date 2019-08-04
 */
public class SysLoginException extends RuntimeException{
    private Integer code;
    public SysLoginException() {
    }

    public SysLoginException(String message) {
        super(message);
    }

    public SysLoginException(Integer code , String message){
        this(StringUtils.isBlank(message)?String.valueOf(code):message);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
