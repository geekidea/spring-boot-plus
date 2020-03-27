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

package io.geekidea.springbootplus.generator.constant;

import java.io.File;

/**
 * 生成器常量
 *
 * @author geekidea
 * @date 2020/3/12
 **/
public interface GeneratorConstant {

    /**
     * 用户目录
     */
    String USER_DIR = "user.dir";
    /**
     * java目录 src/main/java
     */
    String JAVA_DIR = File.separator + "src" + File.separator + "main" + File.separator + "java";
    /**
     * resources目录 src/main/resources
     */
    String RESOURCES_DIR = File.separator + "src" + File.separator + "main" + File.separator + "resources";
    /**
     * mapper目录 src/main/resources/mapper
     */
    String MAPPER_DIR = RESOURCES_DIR + File.separator + "mapper";
    /**
     * 参数
     */
    String PARAM = "param";
    /**
     * 分页参数
     */
    String PAGE_PARAM = "PageParam";
    /**
     * VO
     */
    String VO = "vo";
    /**
     * 查询VO
     */
    String QUERY_VO = "QueryVo";
    /**
     * Service
     */
    String SERVICE = "Service";
    /**
     * Mapper
     */
    String MAPPER = "Mapper";
    /**
     * Mapper XML template路径
     */
    String MAPPER_XML_TEMPLATE_PATH = File.separator + "templates" + File.separator + "mapper.xml.vm";
    /**
     * 分页参数 template路径
     */
    String PAGE_PARAM_TEMPLATE_PATH = File.separator + "templates" + File.separator + "pageParam.java.vm";
    /**
     * 查询VO template路径
     */
    String QUERY_VO_TEMPLATE_PATH = File.separator + "templates" + File.separator + "queryVo.java.vm";
    /**
     * 乐观锁属性名称
     */
    String VERSION = "version";
    /**
     * 逻辑删除属性名称
     */
    String DELETED = "deleted";
    /**
     * Service名称
     */
    String SERVICE_NAME = "%sService";
    /**
     * 自定义继承的Entity类全称，带包名
     */
    String SUPER_ENTITY_CLASS = "io.geekidea.springbootplus.framework.common.entity.BaseEntity";
    /**
     * 自定义继承的Controller类全称，带包名
     */
    String SUPER_CONTROLLER_CLASS = "io.geekidea.springbootplus.framework.common.controller.BaseController";
    /**
     * 自定义继承的Service类全称，带包名
     */
    String SUPER_SERVICE_CLASS = "io.geekidea.springbootplus.framework.common.service.BaseService";
    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    String SUPER_SERVICE_IMPL_CLASS = "io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl";
    /**
     * 分页参数父类全称，带包名
     */
    String SUPER_PAGE_PARAM_CLASS = "io.geekidea.springbootplus.framework.core.pagination.BasePageParam";
    /**
     * 分页排序参数父类全称，带包名
     */
    String SUPER_PAGE_ORDER_PARAM_CLASS = "io.geekidea.springbootplus.framework.core.pagination.BasePageOrderParam";
    /**
     * 公共id参数类全称，带包名
     */
    String ID_PARAM_CLASS = "io.geekidea.springbootplus.framework.common.param.IdParam";
    /**
     * 分页对象类全称，带包名
     */
    String PAGING_CLASS = "io.geekidea.springbootplus.framework.core.pagination.Paging";
    /**
     * 分页信息类全称，带包名
     */
    String PAGE_INFO_CLASS = "io.geekidea.springbootplus.framework.core.pagination.PageInfo";
    /**
     * 公共结果类全称，带包名
     */
    String API_RESULT_CLASS = "io.geekidea.springbootplus.framework.common.api.ApiResult";
    /**
     * 分组验证Add.class类路径
     */
    String VALIDATOR_ADD_PACKAGE = "io.geekidea.springbootplus.framework.core.validator.groups.Add";
    /**
     * 分组验证Update.class类路径
     */
    String VALIDATOR_UPDATE_PACKAGE = "io.geekidea.springbootplus.framework.core.validator.groups.Update";
}
