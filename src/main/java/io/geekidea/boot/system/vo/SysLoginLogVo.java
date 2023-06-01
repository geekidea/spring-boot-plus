package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统登录日志VO
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Data
@Schema(description = "系统登录日志查询结果")
public class SysLoginLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "token")
    private String token;

    @Schema(description = "1:登录，2：登出")
    private Integer type;

    @Schema(description = "是否成功 true:成功/false:失败")
    private Boolean success;

    @Schema(description = "响应码")
    private Integer code;

    @Schema(description = "失败消息记录")
    private String exceptionMessage;

    @Schema(description = "用户环境")
    private String userAgent;

    @Schema(description = "浏览器名称")
    private String browserName;

    @Schema(description = "浏览器版本")
    private String browserVersion;

    @Schema(description = "浏览器引擎名称")
    private String engineName;

    @Schema(description = "浏览器引擎版本")
    private String engineVersion;

    @Schema(description = "系统名称")
    private String osName;

    @Schema(description = "平台名称")
    private String platformName;

    @Schema(description = "是否是手机,0:否,1:是")
    private Boolean mobile;

    @Schema(description = "移动端设备名称")
    private String deviceName;

    @Schema(description = "移动端设备型号")
    private String deviceModel;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

