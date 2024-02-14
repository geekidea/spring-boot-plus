package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户查询参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "系统用户查询参数")
public class SysUserQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "创建开始时间")
    private Date startCreateTime;

    @Schema(description = "创建结束时间")
    private Date endCreateTime;

}

