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

package io.geekidea.springbootplus.system.convert;

import io.geekidea.springbootplus.system.entity.SysDepartment;
import io.geekidea.springbootplus.system.vo.SysDepartmentTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 部门对象属性转换器
 *
 * @author geekidea
 * @date 2019-11-01
 **/
@Mapper
public interface SysDepartmentConvert {

    SysDepartmentConvert INSTANCE = Mappers.getMapper(SysDepartmentConvert.class);

    /**
     * SysDepartment转换成SysDepartmentTreeVo对象
     *
     * @param sysDepartment
     * @return
     */
    SysDepartmentTreeVo entityToTreeVo(SysDepartment sysDepartment);

    /**
     * SysDepartment列表转换成SysDepartmentTreeVo列表
     *
     * @param list
     * @return
     */
    List<SysDepartmentTreeVo> listToTreeVoList(List<SysDepartment> list);

}
