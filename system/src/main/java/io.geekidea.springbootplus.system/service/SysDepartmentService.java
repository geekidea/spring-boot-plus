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

package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.framework.common.service.BaseService;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.system.entity.SysDepartment;
import io.geekidea.springbootplus.system.param.SysDepartmentPageParam;
import io.geekidea.springbootplus.system.vo.SysDepartmentQueryVo;
import io.geekidea.springbootplus.system.vo.SysDepartmentTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 部门 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysDepartmentService extends BaseService<SysDepartment> {

    /**
     * 保存
     *
     * @param sysDepartment
     * @return
     * @throws Exception
     */
    boolean saveSysDepartment(SysDepartment sysDepartment) throws Exception;

    /**
     * 修改
     *
     * @param sysDepartment
     * @return
     * @throws Exception
     */
    boolean updateSysDepartment(SysDepartment sysDepartment) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysDepartment(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysDepartmentQueryVo getSysDepartmentById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysDepartmentPageParam
     * @return
     * @throws Exception
     */
    Paging<SysDepartmentQueryVo> getSysDepartmentPageList(SysDepartmentPageParam sysDepartmentPageParam) throws Exception;

    /**
     * 根据id校验部门是否存在并且已启用
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean isEnableSysDepartment(Long id) throws Exception;

    /**
     * 获取所有可用的部门列表
     * @return
     */
    List<SysDepartment> getAllDepartmentList();

    /**
     * 获取所有可用的部门树形列表
     * @return
     */
    List<SysDepartmentTreeVo> getDepartmentTree();

}
