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

package com.example.foobar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import io.geekidea.springbootplus.framework.common.entity.BaseEntity;
import io.geekidea.springbootplus.framework.core.validator.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * FooBar
 *
 * @author geekidea
 * @since 2020-03-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FooBar对象")
public class FooBar extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "Name不能为空")
    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("Foo")
    private String foo;

    @NotBlank(message = "Bar不能为空")
    @ApiModelProperty("Bar")
    private String bar;

    @ApiModelProperty("Remark")
    private String remark;

    @ApiModelProperty("State，0：Disable，1：Enable")
    private Integer state;

    @ApiModelProperty("Version")
    @Version
    private Integer version;

    @ApiModelProperty("Create Time")
    private Date createTime;

    @ApiModelProperty("Update Time")
    private Date updateTime;

}
