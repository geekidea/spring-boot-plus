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

package io.geekidea.springbootplus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author geekidea
 * @date 2018-11-08
 */
public class DateUtil {

    public static final String formatStr_yyyyMMddHHmmssS_ = "yyyyMMddHHmmss";
    public static final String formatStr_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String formatStr_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String formatStr_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String formatStr_yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String formatStr_yyyyMMdd = "yyyy-MM-dd";
    public static final String formatStr_yyyy = "yyyy";
    public static final String formatStr_yyyy_MM_dd = "yyyyMMdd";
    public static final String formatStr_yyyyMMddDelimiter = "-";

    public static String getYYYYMMDDHHMMSS(Date date){
        if (date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }
}
