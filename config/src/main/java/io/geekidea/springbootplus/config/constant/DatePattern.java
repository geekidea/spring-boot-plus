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

package io.geekidea.springbootplus.config.constant;

/**
 * <p>
 *     日期格式常量
 * </p>
 * @author geekidea
 * @date 2018-11-08
 */
public interface DatePattern {

    /**
     * 年-月-日
     */
    String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 年-月-日 时:分
     */
    String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * 年-月-日 时:分:秒
     */
    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年-月-日 时:分:秒:毫秒
     */
    String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 时:分
     */
    String HH_MM = "HH:mm";
    /**
     * 时:分:秒
     */
    String HH_MM_SS = "HH:mm:ss";
    /**
     * 时:分:秒:毫秒
     */
    String HH_MM_SS_S = "HH:mm:ss:S";
}
