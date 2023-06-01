package io.geekidea.boot.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@TableName("sys_user")
@Schema(description = "系统用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "盐值")
    private String salt;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "电子邮件")
    private String email;

    @Schema(description = "性别，0：女，1：男，默认1")
    private Integer gender;

    @Schema(description = "头像")
    private String head;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

