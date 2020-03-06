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

package io.geekidea.springbootplus.framework.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *     公共ID-NAME-VO对象
 * </p>
 * @author geekidea
 * @since 2018-11-08
 */
@ApiModel("ID-NAME-VO")
public class CommonIdName {

	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("名称")
	private String name;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public CommonIdName(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}


	public CommonIdName() {
		super();
	}


	@Override
	public String toString() {
		return "CommonIdName [id=" + id + ", name=" + name + "]";
	}


	
	
}
