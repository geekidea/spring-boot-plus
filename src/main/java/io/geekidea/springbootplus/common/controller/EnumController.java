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

package io.geekidea.springbootplus.common.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.enums.BaseEnum;
import io.geekidea.springbootplus.common.vo.EnumVo;
import io.geekidea.springbootplus.util.BaseEnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 * 展示实现BaseEnum接口的所有枚举值
 * </p>
 *
 * @author geekidea
 * @date 2018/11/02
 */
@RestController
@Slf4j
public class EnumController {

    /**
     * 枚举包路径
     */
    @Value("${spring-boot-plus.enum-packages}")
    private String[] enumPackages;

    @GetMapping("/enum")
    public ApiResult<String> enumList() {
        log.debug("enumList...");
        return ApiResult.ok(BaseEnumUtil.getEnumMap());
    }

    @PostConstruct
    public void init() {
        try {
            // 获取BaseEnum接口的所有实现
            log.debug("enumPackages:" + Arrays.toString(enumPackages));
            if (ArrayUtils.isEmpty(enumPackages)) {
                log.info("enumPackages为空");
                return;
            }
            Reflections reflections = new Reflections(enumPackages);
            Set<Class<? extends BaseEnum>> set = reflections.getSubTypesOf(BaseEnum.class);
            if (CollectionUtils.isEmpty(set)) {
                return;
            }
            // 循环获取BaseEnum枚举
            for (Class<? extends BaseEnum> clazz : set) {
                List<EnumVo> list = new ArrayList<>();
                Object[] objects = clazz.getEnumConstants();
                Method codeMethod = clazz.getDeclaredMethod("getCode");
                Method descMethod = clazz.getDeclaredMethod("getDesc");
                Map<Integer, String> codeDescMap = new HashMap<>(objects.length);
                for (Object object : objects) {
                    Integer code = (Integer) codeMethod.invoke(object);
                    String desc = (String) descMethod.invoke(object);
                    EnumVo enumVo = new EnumVo();
                    enumVo.setCode(code);
                    enumVo.setDesc(desc);
                    list.add(enumVo);
                    codeDescMap.put(code, desc);
                }
                // 设置map
                BaseEnumUtil.getEnumMap().put(clazz.getSimpleName(), list);
                BaseEnumUtil.getEnumClassMap().put(clazz.getName(), codeDescMap);
            }
            log.debug("baseEnumMap:{}", BaseEnumUtil.getEnumMap());
            log.debug("baseEnumClassMap:{}", BaseEnumUtil.getEnumClassMap());
        } catch (Exception e) {
            log.error("获取BaseEnum枚举map异常", e);
        }
    }

}
