/**
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

package io.geekidea.springbootplus.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 密码加密工具类
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
public class PasswordUtil {
    private static final String KEY = "io.geekidea.springbootplus.springbootplus.pwd.key";
    public static String encrypt(String pwd){
        if (StringUtils.isBlank(pwd)){
            return null;
        }
        pwd = pwd + KEY;
        String newPwd = DigestUtils.sha256Hex(pwd) + DigestUtils.md5Hex(pwd);
        return newPwd;
    }

    public static void main(String[] args) {
        log.debug(encrypt("7c4a8d09ca3762af61e59520943dc26494f8941b"));
    }
}
