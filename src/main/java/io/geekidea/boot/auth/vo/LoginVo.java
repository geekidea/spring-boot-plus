package io.geekidea.boot.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@Schema(description = "登录Vo")
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 3338162302996457084L;

    @Schema(description = "用户Id")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "是否是管理员")
    private boolean admin;

    @Schema(description = "角色ID")
    private List<Long> roleIds;

    @Schema(description = "角色名称集合")
    private List<String> roleNames;

    @Schema(description = "角色编码集合")
    private List<String> roleCodes;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "登录时间")
    private Date loginTime;

}
