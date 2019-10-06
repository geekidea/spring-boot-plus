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

package io.geekidea.springbootplus.common.web.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author geekidea
 * @date 2018-11-08
 */
@ApiModel("分页")
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = -1683800405530086022L;

    @ApiModelProperty("总行数")
    @JSONField(name = "total")
    @JsonProperty("total")
    private long total = 0;

    @ApiModelProperty("数据列表")
    @JSONField(name = "records")
    @JsonProperty("records")
    private List<T> records = Collections.emptyList();

    public Paging() {
    }

    public Paging(IPage page) {
        this.total = page.getTotal();
        this.records = page.getRecords();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
