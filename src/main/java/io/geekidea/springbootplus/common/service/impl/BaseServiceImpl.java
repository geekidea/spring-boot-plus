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

package io.geekidea.springbootplus.common.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.springbootplus.common.enums.OrderEnum;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.common.web.vo.QueryParam;

/**
 * @author geekidea
 * @date 2018-11-08
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    protected Page setPageParam(QueryParam queryParam) {
        return setPageParam(queryParam,null,null);
    }

    protected Page setPageParam(QueryParam queryParam, String defaultOrderField, OrderEnum orderEnum) {
        Page page = new Page();
        page.setCurrent(queryParam.getCurrent());
        page.setSize(queryParam.getSize());
        page.setAsc(queryParam.getAscs());
        page.setDesc(queryParam.getDescs());

        if (orderEnum == OrderEnum.DESC){
            page.setDesc(defaultOrderField);
        }else {
            page.setAsc(defaultOrderField);
        }

        return page;
    }

}
