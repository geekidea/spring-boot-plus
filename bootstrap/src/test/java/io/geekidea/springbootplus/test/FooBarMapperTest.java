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

import com.example.foobar.entity.FooBar;
import com.example.foobar.mapper.FooBarMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author geekidea
 * @date 2020/3/16
 **/
public class FooBarMapperTest extends BaseTest{

    @Autowired
    private FooBarMapper fooBarMapper;

    @Test
    public void test(){
        FooBar addFooBar = new FooBar()
                .setFoo("junit foo")
                .setBar("junit bar")
                .setName("test")
                .setRemark("备注");
        int addResult = fooBarMapper.insert(addFooBar);
        System.out.println("addResult = " + addResult);

        Long addId = addFooBar.getId();
        System.out.println("addId = " + addId);

        FooBar fooBar = fooBarMapper.selectById(addId);
        System.out.println("fooBar = " + fooBar);

    }

}
