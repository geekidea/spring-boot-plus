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

import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户对象属性转换器
 *
 * @author geekidea
 * @date 2019-10-05
 **/
@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    /**
     * 系统用户实体对象转换成登录用户VO对象
     *
     * @param sysUser
     * @return
     */
    LoginSysUserVo sysUserToLoginSysUserVo(io.geekidea.springbootplus.system.entity.SysUser sysUser);

}
