package io.geekidea.boot.framework.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * 数据范围查询
 *
 * @author geekidea
 * @date 2022/3/11
 **/
@Data
@Schema(description = "数据范围查询")
public class DataRangeQuery {

    @Schema(description = "登录用户ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginUserId;

    @Schema(description = "登录角色ID集合", hidden = true)
    @Null(message = "非法参数")
    private List<Long> loginRoleIds;

    @Schema(description = "登录角色编码集合", hidden = true)
    @Null(message = "非法参数")
    private List<String> loginRoleCodes;

    @Schema(description = "登录部门ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginDeptId;

    @Schema(description = "是否是管理员", hidden = true)
    @Null(message = "非法参数")
    private Boolean admin;

}
