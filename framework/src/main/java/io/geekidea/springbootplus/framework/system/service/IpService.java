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

package io.geekidea.springbootplus.framework.system.service;

import io.geekidea.springbootplus.framework.system.entity.Ip;
import io.geekidea.springbootplus.framework.common.service.BaseService;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * IP地址 服务类
 *
 * @author geekidea
 * @since 2020-03-21
 */
public interface IpService extends BaseService<Ip> {

    /**
     * 通过ip地址获取IP对象
     *
     * @param ip
     * @return
     */
    Ip getByIp(@Param("ip") String ip);

}
