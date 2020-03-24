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

package io.geekidea.springbootplus.framework.log.service;

import io.geekidea.springbootplus.framework.common.service.BaseService;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.log.entity.SysOperationLog;
import io.geekidea.springbootplus.framework.log.param.SysOperationLogPageParam;

/**
 * 系统操作日志 服务类
 *
 * @author geekidea
 * @since 2020-03-19
 */
public interface SysOperationLogService extends BaseService<SysOperationLog> {

    /**
     * 保存
     *
     * @param sysOperationLog
     * @return
     * @throws Exception
     */
    boolean saveSysOperationLog(SysOperationLog sysOperationLog) throws Exception;

    /**
     * 修改
     *
     * @param sysOperationLog
     * @return
     * @throws Exception
     */
    boolean updateSysOperationLog(SysOperationLog sysOperationLog) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysOperationLog(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param sysOperationLogQueryParam
     * @return
     * @throws Exception
     */
    Paging<SysOperationLog> getSysOperationLogPageList(SysOperationLogPageParam sysOperationLogPageParam) throws Exception;

}
