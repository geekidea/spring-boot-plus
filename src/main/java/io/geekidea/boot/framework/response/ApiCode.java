package io.geekidea.boot.framework.response;

/**
 * REST API 响应码
 *
 * @author geekidea
 * @since 2022-3-16
 */
public enum ApiCode {

    /**
     * 操作成功
     **/
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     **/
    FAIL(500, "操作失败");

    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] apiCodes = ApiCode.values();
        for (ApiCode apiCode : apiCodes) {
            if (apiCode.getCode() == code) {
                return apiCode;
            }
        }
        return FAIL;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
