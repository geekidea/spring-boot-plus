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

package io.geekidea.springbootplus.shiro.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 盐值包装工具类
 *
 * @author geekidea
 * @date 2019-09-30
 * @since 1.3.0.RELEASE
 **/
public class SaltUtil {

    /**
     * 盐值包装
     *
     * @param secret 配置文件中配置的附加盐值
     * @param salt   数据库中保存的盐值
     * @return
     */
    public static String getSalt(String secret, String salt) {
        if (StringUtils.isBlank(secret) && StringUtils.isBlank(salt)) {
            return null;
        }
        // 加密方法
        String newSalt = DigestUtils.sha256Hex(secret + salt);
        return newSalt;
    }
}
