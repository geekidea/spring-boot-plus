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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  Map构建工具类
 * </p>
 * @author geekidea
 * @date 2019-05-23
 **/
public class MapUtil {

    private Map<String,Object> map;

    private MapUtil(){
        map = new HashMap<>();
    }

    public static MapUtil builder(){
        return new MapUtil();
    }

    public MapUtil put(String key,Object value){
        this.map.put(key,value);
        return this;
    }

    public Map<String,Object> build(){
        return this.map;
    }

}
