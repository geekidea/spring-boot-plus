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

package io.geekidea.springbootplus.system.enums;

import io.geekidea.springbootplus.framework.common.enums.BaseEnum;

/**
 * 层级枚举
 * @author geekidea
 * @date 2019-10-24
 **/
public enum MenuLevelEnum implements BaseEnum {
    /** 一级菜单 **/
    ONE(1, "一级菜单"),
    /** 二级菜单 **/
    TWO(2, "二级菜单"),
    /** 功能菜单 **/
    THREE(3, "功能菜单");

    private Integer code;
    private String desc;

    MenuLevelEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
