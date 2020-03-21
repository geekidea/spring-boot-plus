/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.config.enums;

/**
 * 日志打印类型
 *
 * @author geekidea
 * @date 2020/3/19
 **/
public enum LogPrintType {

    /**
     * 请求和响应日志，按照执行顺序分开打印
     */
    ORDER,
    /**
     * 方法执行结束时，连续分开打印请求和响应日志
     */
    LINE,
    /**
     * 方法执行结束时，合并请求和响应日志，同时打印
     */
    MERGE;

}
