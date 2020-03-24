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

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 生成配置
 *
 * @author geekidea
 * @date 2020/3/13
 **/
@Data
@Accessors(chain = true)
public class GeneratorConfig {
    /**
     * 代码生成策略
     */
    @NestedConfigurationProperty
    public GeneratorStrategy generatorStrategy = GeneratorStrategy.ALL;
    /**
     * 是否生成实体类
     */
    private boolean generatorEntity = true;
    /**
     * 是否生成控制器
     */
    private boolean generatorController = true;
    /**
     * 是否生成service接口
     */
    private boolean generatorService = true;
    /**
     * 是否生成service实现类
     */
    private boolean generatorServiceImpl = true;
    /**
     * 是否生成Mapper
     */
    private boolean generatorMapper = true;
    /**
     * 是否生成Mapper XML
     */
    private boolean generatorMapperXml = true;
    /**
     * 是否生成查询参数
     */
    private boolean generatorPageParam = true;
    /**
     * 是否生成查询VO
     */
    private boolean generatorQueryVo = true;
    /**
     * 是否生成Shiro RequiresPermissions 注解
     */
    private boolean requiresPermissions = true;
    /**
     * 分页列表查询是否排序 true：有排序参数/false：无
     */
    private boolean pageListOrder = true;
    /**
     * 是否生成validation校验，true：生成/false：不生成
     */
    private boolean paramValidation = true;
    /**
     * 是否生成Swagger tags
     * true: @Api(value = "系统用户API",tags = {"系统用户"})
     * false: @Api("系统用户API")
     */
    private boolean swaggerTags = true;

    /**
     * 是否生成系统操作日志注解：@OperationLog
     */
    private boolean operationLog = true;

}
