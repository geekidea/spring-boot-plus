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

package io.geekidea.springbootplus.system.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * IP地址 查询结果对象
 * </p>
 *
 * @author geekidea
 * @date 2019-08-04
 */
@Data
@ApiModel(value="IpQueryVo对象", description="IP地址查询参数")
public class IpQueryVo implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ip开始地址")
    private String ipStart;

    @ApiModelProperty(value = "ip结束地址")
    private String ipEnd;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "运营商")
    private String operator;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "ip开始地址数字")
    private Long ipStartNum;

    @ApiModelProperty(value = "ip结束地址数字")
    private Long ipEndNum;

}