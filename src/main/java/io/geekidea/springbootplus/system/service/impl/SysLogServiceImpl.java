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

package io.geekidea.springbootplus.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.geekidea.springbootplus.system.entity.SysLog;
import io.geekidea.springbootplus.system.mapper.SysLogMapper;
import io.geekidea.springbootplus.system.service.SysLogService;
import io.geekidea.springbootplus.system.web.param.SysLogQueryParam;
import io.geekidea.springbootplus.system.web.vo.SysLogQueryVo;
import io.geekidea.springbootplus.common.enums.OrderEnum;
import io.geekidea.springbootplus.common.web.vo.Paging;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author geekidea
 * @since 2018-11-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLogQueryVo getSysLogById(Serializable id) throws Exception{
        return sysLogMapper.getSysLogById(id);
    }

    @Override
    public Paging<SysLogQueryVo> getSysLogPageList(SysLogQueryParam sysLogQueryParam) throws Exception{
        Page page = setPageParam(sysLogQueryParam,"create_time", OrderEnum.DESC);
        IPage<SysLogQueryVo> iPage = sysLogMapper.getSysLogPageList(page,sysLogQueryParam);
        return new Paging(iPage);
    }

}
