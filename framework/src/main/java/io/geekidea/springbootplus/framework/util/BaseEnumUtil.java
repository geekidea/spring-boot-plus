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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * BaseEnum枚举工具类
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class BaseEnumUtil {

    private static final Map<String, Map<Integer, EnumVo<? extends BaseEnum>>> ENUM_MAP = new LinkedHashMap<>();

    /**
     * 通过code获取描述
     *
     * @param baseEnumType
     * @param code
     * @return
     */
    public static BaseEnum getEnum(Class<? extends BaseEnum> baseEnumType, Integer code) {
        EnumVo<? extends BaseEnum> enumVo = getEnumVo(baseEnumType, code);
        if (enumVo == null) {
            return null;
        }
        return enumVo.getBaseEnum();
    }

    /**
     * 通过code获取描述
     *
     * @param baseEnumType
     * @param code
     * @return
     */
    public static EnumVo<? extends BaseEnum> getEnumVo(Class<? extends BaseEnum> baseEnumType, Integer code) {
        Map<Integer, EnumVo<? extends BaseEnum>> map = getMap(baseEnumType);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return map.get(code);
    }

    /**
     * 判断code在枚举中是否存在
     *
     * @param baseEnumType
     * @param code
     * @return
     */
    public static boolean exists(Class<? extends BaseEnum> baseEnumType, Integer code) {
        EnumVo<? extends BaseEnum> enumVo = getEnumVo(baseEnumType, code);
        if (enumVo == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断code在枚举中是否不存在
     *
     * @param baseEnumType
     * @param code
     * @return
     */
    public static boolean notExists(Class<? extends BaseEnum> baseEnumType, Integer code) {
        return !exists(baseEnumType, code);
    }

    /**
     * 通过code获取描述
     *
     * @param baseEnumType
     * @param code
     * @return
     */
    public static String getDesc(Class<? extends BaseEnum> baseEnumType, Integer code) {
        EnumVo<? extends BaseEnum> enumVo = getEnumVo(baseEnumType, code);
        if (enumVo == null) {
            return null;
        }
        return enumVo.getDesc();
    }

    /**
     * 通过类型获取枚举Map
     *
     * @param baseEnumType
     * @return
     */
    public static Map<Integer, EnumVo<? extends BaseEnum>> getMap(Class<? extends BaseEnum> baseEnumType) {
        return ENUM_MAP.get(baseEnumType.getName());
    }

    /**
     * 通过类型获取枚举code集合
     *
     * @param baseEnumType
     * @return
     */
    public static Set<Integer> getCodeSet(Class<? extends BaseEnum> baseEnumType) {
        Map<Integer, EnumVo<? extends BaseEnum>> map = getMap(baseEnumType);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return map.keySet();
    }

    /**
     * 通过类型获取枚举desc集合
     *
     * @param baseEnumType
     * @return
     */
    public static Collection<EnumVo<? extends BaseEnum>> getDescList(Class<? extends BaseEnum> baseEnumType) {
        Map<Integer, EnumVo<? extends BaseEnum>> map = getMap(baseEnumType);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return map.values();
    }

    public static Map<String, Map<Integer, EnumVo<? extends BaseEnum>>> getEnumMap() {
        return ENUM_MAP;
    }

}
