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

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author geekidea
 * @date 2020/3/2
 **/
public class PropertyColumnUtil {

    private static Map<Class<?>, Map<String, String>> cacheMap = new ConcurrentHashMap<>();

    public static Map<Class<?>, Map<String, String>> getMap() {
        return cacheMap;
    }

    /**
     * 根据实体class，从mybatisplus中获取对应Table的属性列名Map
     *
     * @param clazz
     * @return
     */
    private static Map<String, String> getTableFieldMap(Class<?> clazz) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        if (tableInfo == null) {
            return null;
        }
        List<TableFieldInfo> tableFieldInfos = tableInfo.getFieldList();
        if (CollectionUtils.isEmpty(tableFieldInfos)) {
            return null;
        }
        Map<String, String> cacheMap = tableFieldInfos.stream().collect(Collectors.toMap(TableFieldInfo::getProperty, TableFieldInfo::getColumn));
        return cacheMap;
    }

    /**
     * 从本地缓存中获取属性列名map
     *
     * @param clazz
     * @return
     */
    public static Map<String, String> getPropertyColumnMap(Class<?> clazz) {
        Map<String, String> propertyColumnMap = cacheMap.get(clazz);
        if (MapUtils.isEmpty(propertyColumnMap)) {
            // 从TableInfo中获取，并缓存到内存map中
            Map<String, String> fieldMap = getTableFieldMap(clazz);
            if (MapUtils.isEmpty(fieldMap)) {
                return null;
            } else {
                cacheMap.put(clazz, fieldMap);
                return fieldMap;
            }
        } else {
            return propertyColumnMap;
        }
    }

    /**
     * 通过实体class类型和属性名称，从缓存中获取对应的列名
     *
     * @param clazz
     * @param property
     * @return
     */
    public static String getColumn(Class<?> clazz, String property) {
        Map<String, String> propertyColumnMap = getPropertyColumnMap(clazz);
        if (MapUtils.isEmpty(propertyColumnMap)) {
            throw new IllegalArgumentException("没有找到对应的实体映射对象");
        }
        String column = propertyColumnMap.get(property);
        if (StringUtils.isEmpty(column)) {
            throw new IllegalArgumentException("没有找到对应的列");
        }
        return column;
    }

}
