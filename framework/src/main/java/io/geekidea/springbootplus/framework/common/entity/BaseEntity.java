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

package io.geekidea.springbootplus.framework.common.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 实体父类
 * @author geekidea
 * @date 2018-11-08
 */
@ApiModel("BaseEntity")
public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = -7176390653391227433L;
	
}
