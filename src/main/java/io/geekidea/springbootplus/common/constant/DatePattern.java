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

package io.geekidea.springbootplus.common.constant;

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
    String yyyy_MM_dd = "yyyy-MM-dd";
    /**
     * 年-月-日 时:分
     */
    String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    /**
     * 年-月-日 时:分:秒
     */
    String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年-月-日 时:分:秒:毫秒
     */
    String yyyy_MM_dd_HH_mm_ss_S = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 时:分
     */
    String HH_mm = "HH:mm";
    /**
     * 时:分:秒
     */
    String HH_mm_ss = "HH:mm:ss";
    /**
     * 时:分:秒:毫秒
     */
    String HH_mm_ss_S = "HH:mm:ss:S";
}
