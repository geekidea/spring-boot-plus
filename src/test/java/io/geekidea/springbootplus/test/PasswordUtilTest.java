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

package io.geekidea.springbootplus.test;

import io.geekidea.springbootplus.util.PasswordUtil;
import org.junit.Assert;

/**
 * 密码工具测试类
 *
 * @author geekidea
 * @date 2019-10-05
 **/
public class PasswordUtilTest {
    public static void main(String[] args) {
        String password = "123456";
        String salt = "666";
        String encryptPassword = PasswordUtil.encrypt(password, salt);
        System.out.println(encryptPassword);
        System.out.println(encryptPassword.length());
        Assert.assertEquals(encryptPassword, "3108d080e3d39b4b8ad567405fa878c7dc9a31768b37a8a2c7ec72f511bb66cb");
    }
}
