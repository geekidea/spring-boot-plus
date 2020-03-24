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

package io.geekidea.springbootplus.system.param.sysrole;

import io.geekidea.springbootplus.framework.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 系统角色 查询参数对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRolePageParam对象", description = "系统角色查询参数")
public class SysRolePageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 5068991832024325736L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色状态，0：禁用，1：启用")
    private Integer state;

}
