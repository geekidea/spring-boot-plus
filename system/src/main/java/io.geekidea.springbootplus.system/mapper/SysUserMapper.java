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

package io.geekidea.springbootplus.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.system.param.sysuser.SysUserPageParam;
import io.geekidea.springbootplus.system.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 系统用户 Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysUserQueryVo getSysUserById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysUserPageParam
     * @return
     */
    IPage<SysUserQueryVo> getSysUserPageList(@Param("page") Page page, @Param("param") SysUserPageParam sysUserPageParam);

}
