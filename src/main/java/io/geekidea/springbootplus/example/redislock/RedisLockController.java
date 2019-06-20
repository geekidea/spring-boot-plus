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

package io.geekidea.springbootplus.example.redislock;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.system.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Redis分布式锁示例控制器
 * @author geekidea
 * @date 2018/11/14
 */
@RestController
@RequestMapping("/redisLock")
@Slf4j
public class RedisLockController {

    @Autowired
    private RedisLockExampleService redisLockExampleService;

    @GetMapping("/update")
    public ApiResult<String> update(){

        SysLog sysLog = new SysLog();
        sysLog.setLogId(1060439085228830721L);
        sysLog.setContent("test redis lock " + LocalDateTime.now());
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setType(true);
        boolean flag = redisLockExampleService.update(sysLog);
        if (flag){
            return ApiResult.ok("修改成功");
        }

        return ApiResult.fail("修改失败");
    }

    @GetMapping("/concurrentUpdate")
    public ApiResult<String> concurrentUpdate(){
        int count = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            executorService.execute(() -> {
                        log.debug("i = " + finalI);
                        SysLog sysLog = new SysLog();
                        sysLog.setLogId(1060439085228830721L);
                        sysLog.setContent("test redis lock " + UUID.randomUUID().toString());
                        sysLog.setCreateTime(LocalDateTime.now());
                        sysLog.setType(true);
                        log.debug("sysLog = " + sysLog);
                        boolean flag = redisLockExampleService.update(sysLog);
                        log.debug("flag = " + flag);
                        if (flag){
                            log.debug("修改成功");
                        }else{
                            log.debug("修改失败");
                        }
                    }

            );
        }

        return ApiResult.ok("响应ok...");
    }
}
