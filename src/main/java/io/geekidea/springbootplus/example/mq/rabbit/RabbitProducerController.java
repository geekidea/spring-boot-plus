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

package io.geekidea.springbootplus.example.mq.rabbit;

import io.geekidea.springbootplus.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *     RabbitMQ生产者
 * </p>
 * @author geekidea
 * @since 2018/11/12
 */
@RestController
@RequestMapping("/rabbit")
@Slf4j
public class RabbitProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitProperties rabbitProperties;

    @GetMapping("/sendHello")
    public ApiResult<String> sendHello(String name){
        String message = "hello:" + name + " " + LocalDateTime.now();
        log.debug("send message: {}",message);
        // 发送消息
        rabbitTemplate.convertAndSend(
                rabbitProperties.getTemplate().getExchange(),
                rabbitProperties.getTemplate().getRoutingKey(),
                message
        );

        return ApiResult.ok("send ok...");
    }


    @GetMapping("/batchSend")
    public ApiResult<String> batchSend(String name){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String message = i + " hello:" + name + " " + LocalDateTime.now();
                    log.debug("send message: {}",message);
                    // 发送消息
                    rabbitTemplate.convertAndSend(
                            rabbitProperties.getTemplate().getExchange(),
                            rabbitProperties.getTemplate().getRoutingKey(),
                            message
                    );
                }
            }
        });
        thread.start();

        return ApiResult.ok("send ok...");
    }

}
