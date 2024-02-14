package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统用户Excel导入参数映射
 *
 * @author geekidea
 * @since 2023-12-25
 */
@Data
@Schema(description = "系统用户Excel导入参数映射")
public class SysUserExcelDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "电子邮件")
    private String email;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "角色id")
    private Long roleId;

}


