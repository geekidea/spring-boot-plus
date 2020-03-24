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

package io.geekidea.springbootplus.config.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 配置文件属性映射为静态属性
 *
 * @author geekidea
 * @date 2019-10-11
 **/
@Slf4j
@Data
@Component
public class SpringBootPlusStaticProperties {

    public static String INFO_PROJECT_VERSION = "";

    @Value("${info.project-version}")
    private String infoProjectVersion;

    @PostConstruct
    public void init() {
        INFO_PROJECT_VERSION = this.infoProjectVersion;
        log.debug("INFO_PROJECT_VERSION:" + INFO_PROJECT_VERSION);
    }

}
