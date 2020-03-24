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

package io.geekidea.springbootplus.generator.config.query;

import com.baomidou.mybatisplus.generator.config.querys.SqlServerQuery;

/**
 * SQLServer自定义生成器查询SQL
 *
 * @author geekidea
 * @date 2020-3-8
 **/
public class SpringBootPlusSqlServerQuery extends SqlServerQuery {

    /**
     * fix mybatisplus3.3.1 SQLServer SQL过滤表，TABLE_NAME报错问题
     * @return
     */
    @Override
    public String tablesSql() {
        return  "select TABLE_NAME,COMMENTS from (" +
                "   select cast(so.name as varchar(500)) as TABLE_NAME, " +
                "   cast(sep.value as varchar(500)) as COMMENTS from sysobjects so " +
                "   left JOIN sys.extended_properties sep on sep.major_id=so.id and sep.minor_id=0 " +
                "   where (xtype='U' or xtype='v')" +
                ") tb " +
                "where 1 = 1 ";

    }
}
