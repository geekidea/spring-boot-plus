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

package io.geekidea.springbootplus.system.web.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.system.service.IpService;
import io.geekidea.springbootplus.system.web.vo.IpVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 * @auth geekidea
 * @date 2019-05-22
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IpService ipService;

    @GetMapping("/hello/{name}")
    public ApiResult hello(@PathVariable String name){
        System.out.println("name = " + name);
        return ApiResult.ok();
    }

    @GetMapping("/ip")
    public ApiResult getByIp(){
        String ip = "218.88.127.20";
        IpVo ipVo = ipService.getByIp(ip);
        return ApiResult.ok(ipVo);
    }

}
