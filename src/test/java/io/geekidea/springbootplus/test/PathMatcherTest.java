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

import org.springframework.util.AntPathMatcher;

/**
 * @author geekidea
 * @date 2019-10-10
 **/
public class PathMatcherTest {

    public static void main(String[] args) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String pattern = "/hello/**";
        String path = "/hello/world";
        boolean flag = pathMatcher.match(pattern, path);
        System.out.println(flag);

    }

}
