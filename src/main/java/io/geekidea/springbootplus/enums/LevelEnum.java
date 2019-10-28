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

package io.geekidea.springbootplus.enums;

import io.geekidea.springbootplus.common.enums.BaseTypeStateEnum;

/**
 * @author geekidea
 * @date 2019-10-24
 **/
public enum LevelEnum implements BaseTypeStateEnum {
    ONE(1, "一级菜单"),
    TWO(2, "二级菜单"),
    THREE(3, "功能菜单");

    private Integer key;
    private String value;

    LevelEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
