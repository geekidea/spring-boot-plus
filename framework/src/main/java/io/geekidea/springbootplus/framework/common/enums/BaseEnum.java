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

package io.geekidea.springbootplus.framework.common.enums;


/**
 * 枚举类型父接口
 *
 * @author geekidea
 * @date 2018-11-08
 */
public interface BaseEnum {

    /**
     * 获取枚举标识
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取枚举描述
     *
     * @return
     */
    String getDesc();

    /**
     * 通过枚举类型和code值获取对应的枚举类型
     * @param enumType
     * @param code
     * @param <T>
     * @return
     */
    static <T extends BaseEnum> T valueOf(Class<? extends BaseEnum> enumType, Integer code) {
        if (enumType == null || code == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            int enumCode = enumConstant.getCode();
            if (code.equals(enumCode)) {
                return enumConstant;
            }
        }
        return null;
    }

}
