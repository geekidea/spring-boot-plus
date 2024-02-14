package io.geekidea.boot.auth.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求路径登录校验拦截策略
 * 默认拦截包含路径开头的所有请求(/app/**)，在不需要验证登录的controller方法加上@IgnoreLogin，表示此方法不需要登录就能调用
 * 默认不拦截包含路径开头的所有请求(/app/**)，在需要登录的controller方法上加上注解@Login，表示此方法必须登录才能调用
 * @author geekidea
 * @date 2024/1/31
 **/
public enum LoginInterceptStrategy {
    LOGIN(1, "需要登录"),
    IGNORE_LOGIN(2, "忽略登录");

    private Integer code;
    private String desc;

    LoginInterceptStrategy(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, LoginInterceptStrategy> map = new HashMap<>();

    static {
        for (LoginInterceptStrategy type : values()) {
            map.put(type.code, type);
        }
    }

    public static Integer getCode(LoginInterceptStrategy type) {
        if (type == null) {
            return null;
        }
        return type.code;
    }

    public static LoginInterceptStrategy get(Integer code) {
        LoginInterceptStrategy type = map.get(code);
        if (type == null) {
            return null;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        LoginInterceptStrategy type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

}
