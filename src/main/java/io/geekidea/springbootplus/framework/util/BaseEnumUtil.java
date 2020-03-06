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

package io.geekidea.springbootplus.framework.util;

import io.geekidea.springbootplus.framework.common.enums.BaseEnum;
import io.geekidea.springbootplus.framework.common.vo.EnumVo;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * BaseEnum枚举工具类
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class BaseEnumUtil {

    private static final Map<String, List<EnumVo>> ENUM_MAP = new LinkedHashMap<>();
    private static final Map<String, Map<Integer, String>> ENUM_CLASS_MAP = new LinkedHashMap<>();

    /**
     * 通过类型获取枚举Map
     *
     * @param clazz
     * @return
     */
    public static Map<Integer, String> getMap(Class<? extends BaseEnum> clazz) {
        return ENUM_CLASS_MAP.get(clazz.getName());
    }

    /**
     * 通过类型获取枚举code集合
     *
     * @param clazz
     * @return
     */
    public static Set<Integer> getCodeSet(Class<? extends BaseEnum> clazz) {
        Map<Integer, String> map =  ENUM_CLASS_MAP.get(clazz.getName());
        if (MapUtils.isEmpty(map)){
            return null;
        }
        return map.keySet();
    }

    /**
     * 通过类型获取枚举desc集合
     *
     * @param clazz
     * @return
     */
    public static Collection<String> getDescList(Class<? extends BaseEnum> clazz) {
        Map<Integer, String> map =  ENUM_CLASS_MAP.get(clazz.getName());
        if (MapUtils.isEmpty(map)){
            return null;
        }
        return map.values();
    }

    /**
     * 通过code获取name
     *
     * @param clazz
     * @param code
     * @return
     */
    public static String getDesc(Class<? extends BaseEnum> clazz, Integer code) {
        Map<Integer, String> map = ENUM_CLASS_MAP.get(clazz.getName());
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return map.get(code);
    }

    /**
     * 判断code在枚举中是否存在
     *
     * @param clazz
     * @param code
     * @return
     */
    public static boolean exists(Class<? extends BaseEnum> clazz, Integer code) {
        String name = getDesc(clazz, code);
        if (StringUtils.isBlank(name)) {
            return false;
        }
        return true;
    }

    /**
     * 判断code在枚举中是否不存在
     *
     * @param clazz
     * @param code
     * @return
     */
    public static boolean notExists(Class<? extends BaseEnum> clazz, Integer code) {
        return !exists(clazz, code);
    }

    public static Map<String, List<EnumVo>> getEnumMap(){
        return ENUM_MAP;
    }

    public static Map<String, Map<Integer, String>> getEnumClassMap(){
        return ENUM_CLASS_MAP;
    }

}
