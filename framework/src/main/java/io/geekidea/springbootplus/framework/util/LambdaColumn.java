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
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据lambda表达式获取数据库列名
 *
 * @author geekidea
 * @date 2019/12/22
 **/
public class LambdaColumn<T> {

    private static Map<String, Map<String, String>> ENTITY_COLUMN_MAP = new ConcurrentHashMap<>();

    public static <T> LambdaColumn<T> lambda() {
        return new LambdaColumn<>();
    }

    public String get(SFunction<T, ?> func) {
        SerializedLambda lambda = LambdaUtils.resolve(func);
        String methodName = lambda.getImplMethodName();
        String methodPropertyName = null;
        if (methodName.startsWith("get")) {
            methodPropertyName = methodName.substring(3);
            methodPropertyName = methodPropertyName.substring(0, 1).toLowerCase() + methodPropertyName.substring(1);

        }
        Class<?> cls = lambda.getImplClass();
        String className = lambda.getImplClassName();
        if (ENTITY_COLUMN_MAP.containsKey(className)) {
            return ENTITY_COLUMN_MAP.get(className).get(methodPropertyName);
        }

        Map<String, String> map = getPropertyColumnMap(cls);
        if (MapUtils.isNotEmpty(map)) {
            ENTITY_COLUMN_MAP.put(className, map);
        }
        return ENTITY_COLUMN_MAP.get(className).get(methodPropertyName);
    }

    /**
     * 从mybatisplus的TableInfo类中获取列名map信息
     *
     * @param cls
     * @return
     */
    private Map<String, String> getPropertyColumnMap(Class<?> cls) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        List<TableFieldInfo> tableFieldInfos = tableInfo.getFieldList();
        if (CollectionUtils.isEmpty(tableFieldInfos)) {
            return null;
        }

        Map<String, String> map = new ConcurrentHashMap<>();
        String keyProperty = tableInfo.getKeyProperty();
        String keyColumn = tableInfo.getKeyColumn();
        map.put(keyProperty, keyColumn);

        for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
            String column = tableFieldInfo.getColumn();
            String property = tableFieldInfo.getProperty();
            map.put(property, column);
        }
        return map;
    }

}
