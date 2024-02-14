package io.geekidea.boot.generator.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求映射风格 1：默认，2：restful，3：全部小写，4：中横线，5：下划线
 *
 * @author geekidea
 * @date 2024/1/11
 **/
public enum RequestMappingStyle {

    DEFAULT(1, "默认"),
    RESTFUL(2, "restful"),
    SMALL_LETTER(3, "全部小写字母"),
    HYPHEN(4, "中横线连接符"),
    UNDERLINE(5, "下划线");

    private Integer code;

    private String desc;

    RequestMappingStyle(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, RequestMappingStyle> map = new HashMap<>();

    static {
        for (RequestMappingStyle style : values()) {
            map.put(style.code, style);
        }
    }

    public static Integer getCode(RequestMappingStyle type) {
        if (type == null) {
            return DEFAULT.code;
        }
        return type.code;
    }

    public static RequestMappingStyle get(Integer code) {
        RequestMappingStyle style = map.get(code);
        if (style == null) {
            return DEFAULT;
        }
        return style;
    }

    public static String getDesc(Integer code) {
        RequestMappingStyle style = get(code);
        if (style == null) {
            return null;
        }
        return style.getDesc();
    }

}
