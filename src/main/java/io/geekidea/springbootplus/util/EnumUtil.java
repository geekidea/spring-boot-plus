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

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * 枚举工具类
 * @author geekidea
 * @date 2018-11-08
 */
public class EnumUtil {

    /**
     * 通过key获取name
     * @param clazz
     * @param key
     * @return
     */
    public static String getEnum(Class<?> clazz,Integer key){
       try{
           Object[] objects = clazz.getEnumConstants();
           Method keyMethod = clazz.getDeclaredMethod("getKey");
           Method valueMethod = clazz.getDeclaredMethod("getValue");
           for (Object object : objects) {
               Integer k = (Integer) keyMethod.invoke(object);
               if (k.equals(key)){
                   String value = (String) valueMethod.invoke(object);
                   return value;
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }

    /**
     * 判断key在枚举中是否存在
     * @param clazz
     * @param key
     * @return
     */
    public static boolean exists(Class<?> clazz,Integer key){
        String name = getEnum(clazz,key);
        if (StringUtils.isBlank(name)){
            return false;
        }
        return true;
    }

    /**
     * 判断key在枚举中是否不存在
     * @param clazz
     * @param key
     * @return
     */
    public static boolean notExists(Class<?> clazz,Integer key){
        return !exists(clazz,key);
    }

}
