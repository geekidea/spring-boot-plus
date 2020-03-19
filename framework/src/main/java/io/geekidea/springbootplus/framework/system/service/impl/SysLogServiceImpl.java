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

package io.geekidea.springbootplus.framework.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.framework.core.pagination.PageUtil;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.system.entity.SysLog;
import io.geekidea.springbootplus.framework.system.entity.SysUser;
import io.geekidea.springbootplus.framework.system.mapper.SysLogMapper;
import io.geekidea.springbootplus.framework.system.param.SysLogPageParam;
import io.geekidea.springbootplus.framework.system.service.SysLogService;
import io.geekidea.springbootplus.framework.system.vo.SysLogQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
@Slf4j
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLogQueryVo getSysLogById(Serializable id) throws Exception {
        return sysLogMapper.getSysLogById(id);
    }

    @Override
    public Paging<SysLogQueryVo> getSysLogPageList(SysLogPageParam sysLogPageParam) throws Exception {
        Page page = PageUtil.getPage(sysLogPageParam, OrderItem.desc(getLambdaColumn(SysLog::getCreateTime)));
        IPage<SysLogQueryVo> iPage = sysLogMapper.getSysLogPageList(page, sysLogPageParam);
        return new Paging(iPage);
    }

}
