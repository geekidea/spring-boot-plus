/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.security.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  登录用户redis对象，后台使用
 * </p>
 * @auth geekidea
 * @date 2019-05-15
 **/
@Data
@ApiModel("系统用户登录值对象")
public class LoginSysUserRedisVo implements Serializable {

    private static final long serialVersionUID = -4497185071769175695L;

    /**
     * 登录对象vo
     */
    private LoginSysUserVo loginSysUserVo;

    /**
     * jwt token对象
     */
    private JwtTokenRedisVo jwtTokenRedisVo;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 登录ip
     */
    private ClientInfo clientInfo;

}
