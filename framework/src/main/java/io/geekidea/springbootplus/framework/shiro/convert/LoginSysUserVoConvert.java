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

package io.geekidea.springbootplus.framework.shiro.convert;

import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserRedisVo;
import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 登录系统用户VO对象属性复制转换器
 *
 * @author geekidea
 * @date 2020/3/24
 **/
@Mapper
public interface LoginSysUserVoConvert {

    LoginSysUserVoConvert INSTANCE = Mappers.getMapper(LoginSysUserVoConvert.class);

    /**
     * LoginSysUserVo对象转换成LoginSysUserRedisVo
     *
     * @param loginSysUserVo
     * @return
     */
    LoginSysUserRedisVo voToRedisVo(LoginSysUserVo loginSysUserVo);

}
