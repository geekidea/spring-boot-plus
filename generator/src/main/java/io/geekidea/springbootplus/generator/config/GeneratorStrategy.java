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

package io.geekidea.springbootplus.generator.config;

/**
 * 生成策略
 * SIMPLE 生成最基本的代码
 * SINGLE 生成单表操作代码
 * ALL 生成所有的代码
 *
 * @author geekidea
 * @date 2020/3/13
 **/
public enum GeneratorStrategy {
    SIMPLE, SINGLE, ALL
}
