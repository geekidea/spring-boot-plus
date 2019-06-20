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

package io.geekidea.springbootplus.common.web.vo;

import io.geekidea.springbootplus.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 查询参数
 * @author geekidea
 * @since 2018-11-08
 */
@ApiModel
public class QueryParam implements Serializable{
	private static final long serialVersionUID = 7305222607977143006L;
	@ApiModelProperty(value = "页码,默认为1")
	private Integer current = CommonConstant.DEFAULT_PAGE_INDEX;
	@ApiModelProperty(value = "页大小,默认为10")
	private Integer size = CommonConstant.DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "升序排序")
    private String[] ascs;
    @ApiModelProperty(value = "降序排序")
    private String[] descs;
    @ApiModelProperty(value = "搜索字符串")
    private String keyword;

	public QueryParam() {

    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
	    if (current == null || current == 0){
	        this.current = CommonConstant.DEFAULT_PAGE_INDEX;
        }else{
            this.current = current;
        }
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
	    if (size == null || size == 0){
	        this.size = CommonConstant.DEFAULT_PAGE_SIZE;
        }else{
            this.size = size;
        }
    }

    public String[] getAscs() {
        return ascs;
    }

    public void setAscs(String[] ascs) {
        this.ascs = ascs;
    }

    public String[] getDescs() {
        return descs;
    }

    public void setDescs(String[] descs) {
        this.descs = descs;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setDefaultOrder(String orderByField,boolean isAsc){
	    if (StringUtils.isBlank(orderByField)){
	        return;
        }

    }
}
