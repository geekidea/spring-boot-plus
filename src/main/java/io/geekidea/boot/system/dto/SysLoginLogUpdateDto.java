package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改系统登录日志参数
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Data
@Schema(description = "修改系统登录日志参数")
public class SysLoginLogUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "请求ID")
    @Length(max = 32, message = "请求ID长度超过限制")
    private String requestId;

    @Schema(description = "用户名称")
    @Length(max = 32, message = "用户名称长度超过限制")
    private String username;

    @Schema(description = "IP")
    @Length(max = 15, message = "IP长度超过限制")
    private String ip;

    @Schema(description = "token")
    @Length(max = 32, message = "token长度超过限制")
    private String token;

    @Schema(description = "1:登录，2：登出")
    private Integer type;

    @Schema(description = "是否成功 true:成功/false:失败")
    private Boolean success;

    @Schema(description = "响应码")
    private Integer code;

    @Schema(description = "失败消息记录")
    @Length(max = 300, message = "失败消息记录长度超过限制")
    private String exceptionMessage;

    @Schema(description = "用户环境")
    @Length(max = 300, message = "用户环境长度超过限制")
    private String userAgent;

    @Schema(description = "浏览器名称")
    @Length(max = 100, message = "浏览器名称长度超过限制")
    private String browserName;

    @Schema(description = "浏览器版本")
    @Length(max = 100, message = "浏览器版本长度超过限制")
    private String browserVersion;

    @Schema(description = "浏览器引擎名称")
    @Length(max = 100, message = "浏览器引擎名称长度超过限制")
    private String engineName;

    @Schema(description = "浏览器引擎版本")
    @Length(max = 100, message = "浏览器引擎版本长度超过限制")
    private String engineVersion;

    @Schema(description = "系统名称")
    @Length(max = 100, message = "系统名称长度超过限制")
    private String osName;

    @Schema(description = "平台名称")
    @Length(max = 100, message = "平台名称长度超过限制")
    private String platformName;

    @Schema(description = "是否是手机,0:否,1:是")
    private Boolean mobile;

    @Schema(description = "移动端设备名称")
    @Length(max = 100, message = "移动端设备名称长度超过限制")
    private String deviceName;

    @Schema(description = "移动端设备型号")
    @Length(max = 100, message = "移动端设备型号长度超过限制")
    private String deviceModel;

}


