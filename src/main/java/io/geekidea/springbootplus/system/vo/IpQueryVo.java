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

package io.geekidea.springbootplus.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * IP地址 查询结果对象
 * </p>
 *
 * @author geekidea
 * @date 2019-10-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "IpQueryVo对象", description = "IP地址查询参数")
public class IpQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

private String ipStart;

private String ipEnd;

private String area;

private String operator;

private Long id;

private Long ipStartNum;

private Long ipEndNum;

}