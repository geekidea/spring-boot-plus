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

package io.geekidea.springbootplus.example.mq.kafka;

import io.geekidea.springbootplus.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *     Kafka生产者
 * </p>
 * @author geekidea
 * @since 2018/11/13
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaProperties kafkaProperties;

    @GetMapping("/sendHello")
    public ApiResult<String> sendHello(String name){
        String message = "hello:" + name + " " + LocalDateTime.now();
        log.debug("send message: {}",message);
        // 发送消息
        kafkaTemplate.send(
                kafkaProperties.getTemplate().getDefaultTopic(),
                message);
        return ApiResult.ok("send ok...");
    }

    @GetMapping("/sendHelloCallback")
    public ApiResult<String> sendHelloCallback(String name){
        String message = "hello:" + name + " " + LocalDateTime.now();
        log.debug("send message: {}",message);
        // 发送消息
        ListenableFuture<SendResult> future = kafkaTemplate.send(
                kafkaProperties.getTemplate().getDefaultTopic(),
                message);
        future.addCallback(new ListenableFutureCallback<SendResult>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getLocalizedMessage(),ex);
            }

            @Override
            public void onSuccess(SendResult result) {
                log.info(result.toString());
            }
        });
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
                    kafkaTemplate.send(
                            kafkaProperties.getTemplate().getDefaultTopic(),
                            message
                    );
                }
            }
        });
        thread.start();

        return ApiResult.ok("send ok...");
    }
}
