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

package io.geekidea.springbootplus.framework.ip.service.impl;

import io.geekidea.springbootplus.config.constant.CommonConstant;
import io.geekidea.springbootplus.framework.ip.entity.Ip;
import io.geekidea.springbootplus.framework.ip.mapper.IpMapper;
import io.geekidea.springbootplus.framework.ip.service.IpService;
import io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * IP地址 服务实现类
 *
 * @author geekidea
 * @since 2020-03-21
 */
@Slf4j
@Service
public class IpServiceImpl extends BaseServiceImpl<IpMapper, Ip> implements IpService {

    @Autowired
    private IpMapper ipMapper;


    @Override
    public Ip getByIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        if (CommonConstant.LOCALHOST_IP.equals(ip)) {
            return new Ip().setArea(CommonConstant.LOCALHOST_IP_NAME);
        }
        if (CommonConstant.LAN_IP.equals(ip)) {
            return new Ip().setArea(CommonConstant.LAN_IP_NAME);
        }
        return ipMapper.getByIp(ip);
    }
}
