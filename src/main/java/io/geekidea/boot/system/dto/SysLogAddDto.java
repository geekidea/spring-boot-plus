package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 添加系统日志参数
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Data
@Schema(description = "添加系统日志参数")
public class SysLogAddDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "日志链路ID")
    @Length(max = 32, message = "日志链路ID长度超过限制")
    private String traceId;

    @Schema(description = "请求时间")
    @Length(max = 100, message = "请求时间长度超过限制")
    private String requestTime;

    @Schema(description = "全路径")
    @Length(max = 1000, message = "全路径长度超过限制")
    private String requestUrl;

    @Schema(description = "日志名称")
    @Length(max = 500, message = "日志名称长度超过限制")
    private String logName;

    @Schema(description = "请求方式，GET/POST")
    @Length(max = 10, message = "请求方式，GET/POST长度超过限制")
    private String requestMethod;

    @Schema(description = "内容类型")
    @Length(max = 200, message = "内容类型长度超过限制")
    private String contentType;

    @Schema(description = "是否是JSON请求映射参数")
    private Boolean isRequestBody;

    @Schema(description = "请求参数")
    private String requestParam;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    @Length(max = 100, message = "用户名长度超过限制")
    private String username;

    @Schema(description = "请求ip")
    @Length(max = 15, message = "请求ip长度超过限制")
    private String requestIp;

    @Schema(description = "0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件")
    private Integer logType;

    @Schema(description = "0:失败,1:成功")
    private Boolean responseSuccess;

    @Schema(description = "响应结果状态码")
    private Integer responseCode;

    @Schema(description = "响应结果消息")
    private String responseMessage;

    @Schema(description = "响应数据")
    private String responseData;

    @Schema(description = "异常类名称")
    @Length(max = 200, message = "异常类名称长度超过限制")
    private String exceptionName;

    @Schema(description = "异常信息")
    private String exceptionMessage;

    @Schema(description = "响应时间")
    @Length(max = 100, message = "响应时间长度超过限制")
    private String responseTime;

    @Schema(description = "耗时，单位：毫秒")
    private Long diffTime;

    @Schema(description = "来源地址")
    @Length(max = 500, message = "来源地址长度超过限制")
    private String referer;

    @Schema(description = "请求来源")
    @Length(max = 100, message = "请求来源长度超过限制")
    private String requestSource;

    @Schema(description = "用户环境")
    @Length(max = 500, message = "用户环境长度超过限制")
    private String userAgent;

}


