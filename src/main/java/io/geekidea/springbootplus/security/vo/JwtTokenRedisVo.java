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

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author geekidea
 * @date 2019-05-23
 **/
@Data
public class JwtTokenRedisVo implements Serializable {
    private static final long serialVersionUID = 5631646796482815114L;

    /**
     * 登录token
     */
    private String token;

    /**
     * 登录token md5
     */
    private String tokenMd5;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 过期时间
     */
    private Date expiredDate;
}
