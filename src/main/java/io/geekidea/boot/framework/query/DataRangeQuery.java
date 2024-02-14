package io.geekidea.boot.framework.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 数据范围查询
 *
 * @author geekidea
 * @date 2022/3/11
 **/
@Data
@Schema(description = "数据范围查询")
public class DataRangeQuery implements Serializable {

    private static final long serialVersionUID = 43552489665526540L;

    @Schema(description = "管理后台或APP登录用户ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginCommonUserId;

    @Schema(description = "是否是管理后台的管理员", hidden = true)
    @Null(message = "非法参数")
    private Boolean loginAdmin;

    @Schema(description = "管理后台登录用户ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginUserId;

    @Schema(description = "管理后台登录角色ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginRoleId;

    @Schema(description = "管理后台登录角色编码", hidden = true)
    @Null(message = "非法参数")
    private String loginRoleCode;

    @Schema(description = "移动端登录用户角色ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginAppUserRoleId;

    @Schema(description = "移动端登录用户角色编码", hidden = true)
    @Null(message = "非法参数")
    private String loginAppUserRoleCode;

    @Schema(description = "APP登录用户ID", hidden = true)
    @Null(message = "非法参数")
    private Long loginAppUserId;

}
