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
import java.util.List;

/**
 * 系统权限树形列表VO
 *
 * @author geekidea
 * @date 2019-10-26
 **/
@Data
@Accessors(chain = true)
@ApiModel(value = "SysPermissionTreeVo对象", description = "系统权限树形列表")
public class SysPermissionTreeVo implements Serializable {
    private static final long serialVersionUID = 2738804574228359190L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("路径")
    private String url;

    @ApiModelProperty("唯一编码")
    private String code;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("类型，1：菜单，2：按钮")
    private Integer type;

    @ApiModelProperty("层级，1：第一级，2：第二级，N：第N级")
    private Integer level;

    @ApiModelProperty("状态，0：禁用，1：启用")
    private Integer state;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("子节点集合")
    private List<SysPermissionTreeVo> children;

}