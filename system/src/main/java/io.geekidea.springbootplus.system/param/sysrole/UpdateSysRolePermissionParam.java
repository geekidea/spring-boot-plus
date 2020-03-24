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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author geekidea
 * @date 2020/3/2
 **/
@Data
@ApiModel("修改系统角色权限参数")
public class UpdateSysRolePermissionParam implements Serializable {

    private static final long serialVersionUID = -672108684986772098L;

    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @ApiModelProperty("权限ID集合")
    private List<Long> permissionIds;
}
