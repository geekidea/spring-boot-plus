/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.core.pagination;

import io.geekidea.springbootplus.framework.core.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 *
 * @author geekidea
 * @since 2018-11-08
 */
@Data
@ApiModel("查询参数对象")
public abstract class BasePageParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;

    @ApiModelProperty(value = "页码,默认为1", example = "1")
    private Long pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;

    @ApiModelProperty(value = "页大小,默认为10", example = "10")
    private Long pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

    @ApiModelProperty(value = "搜索字符串", example = "")
    private String keyword;

    /**
     * 每页显示3行数据
     * <p>
     * 根据id顺序排列
     * 1,2,3 lastRowId：3
     * <p>
     * where id > 3 order by id limit 0,3
     * 4,5,6 lastRowId：6
     * <p>
     * where id > 6 order by id limit 0,3
     * 7,8,9
     * <p>
     * 根据id降序排列
     * 9,8,7 lastRowId：7
     * <p>
     * where id < 7 order by id desc limit 0,3
     * 6,5,4 lastRowId：4
     * <p>
     * where id < 4 order by id desc limit 0,3
     * 3,2,1 lastRowId：1
     */
    @ApiModelProperty("当前页最后一行分页标识，需作为参数回传")
    private Long lastRowLimitValue;

    public void setPageIndex(Long pageIndex) {
        if (pageIndex == null || pageIndex <= 0) {
            this.pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize <= 0) {
            this.pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

}
