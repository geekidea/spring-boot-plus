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

import io.geekidea.springbootplus.framework.util.PhoneUtil;
import org.junit.Test;

/**
 * 手机号码工具类测试
 *
 * @author geekidea
 * @date 2020/2/26
 **/
public class PhoneUtilTest {

    /**
     * 手机号码脱敏测试
     */
    @Test
    public void test() {
        String phone = PhoneUtil.desensitize("18812345678");
        System.out.println("phone = " + phone);
    }

}
