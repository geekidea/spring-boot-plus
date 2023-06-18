package io.geekidea.boot.framework.response;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API 返回结果
 *
 * @author geekidea
 * @since 2022-3-16
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@Schema(description = "响应结果")
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 8004487252556526569L;

    @Schema(description = "响应编码 200：成功，500：失败")
    private int code;

    @Schema(description = "响应结果 true：成功，false：失败")
    private boolean success;

    @Schema(description = "响应消息")
    private String msg;

    @Schema(description = "响应结果数据")
    private T data;

    @Schema(description = "响应时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public ApiResult() {
        time = new Date();
    }

    public static ApiResult result(boolean flag) {
        if (flag) {
            return success();
        }
        return fail();
    }

    public static ApiResult result(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static ApiResult result(ApiCode apiCode, Object data) {
        return result(apiCode, null, data);
    }

    public static ApiResult result(ApiCode apiCode, String message, Object data) {
        boolean success = false;
        if (apiCode.getCode() == ApiCode.SUCCESS.getCode()) {
            success = true;
        }
        String outErrorMessage;
        if (StringUtils.isBlank(message)) {
            outErrorMessage = apiCode.getMsg();
        } else {
            outErrorMessage = message;
        }
        return ApiResult.builder()
                .code(apiCode.getCode())
                .msg(outErrorMessage)
                .data(data)
                .success(success)
                .time(new Date())
                .build();
    }

    public static ApiResult success() {
        return success(null);
    }

    public static ApiResult success(Object data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static ApiResult success(Object data, String message) {
        return result(ApiCode.SUCCESS, message, data);
    }

    public static ApiResult okMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return success(map);
    }

    public static ApiResult fail(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static ApiResult fail(String message) {
        return result(ApiCode.FAIL, message, null);

    }

    public static ApiResult fail(ApiCode apiCode, Object data) {
        if (ApiCode.SUCCESS == apiCode) {
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode, data);

    }

    public static ApiResult fail(Integer errorCode, String message) {
        return new ApiResult()
                .setSuccess(false)
                .setCode(errorCode)
                .setMsg(message);
    }

    public static ApiResult fail(String key, Object value) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return result(ApiCode.FAIL, map);
    }

    public static ApiResult fail() {
        return fail(ApiCode.FAIL);
    }

    public static ApiResult fail(Object t) {
        return new ApiResult().setCode(ApiCode.FAIL.getCode()).setMsg(ApiCode.FAIL.getMsg());
    }
}
