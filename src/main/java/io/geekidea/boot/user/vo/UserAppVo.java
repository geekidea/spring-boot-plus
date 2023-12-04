package io.geekidea.boot.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * App用户信息VO
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App用户信息查询结果")
public class UserAppVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "盐值")
    private String salt;

    @Schema(description = "微信openid")
    private String openid;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "头像")
    private String head;

    @Schema(description = "是否是VIP，1：是，0：否")
    private Boolean isVip;

    @Schema(description = "VIP等级 1：普通会员，2：黄金会员，3：铂金会员，4：钻石会员")
    private Integer vipLevel;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

