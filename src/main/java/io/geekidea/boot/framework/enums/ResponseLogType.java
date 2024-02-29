package io.geekidea.boot.framework.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekidea
 * @date 2024/2/12
 **/
public enum ResponseLogType {

    FULL(1, "全部"),
    PART(2, "部分"),
    NONE(3, "无");

    private static final Map<Integer, ResponseLogType> map = new HashMap<>();

    static {
        for (ResponseLogType type : values()) {
            map.put(type.code, type);
        }
    }

    private Integer code;
    private String desc;

    ResponseLogType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Integer getCode(ResponseLogType type) {
        if (type == null) {
            return PART.code;
        }
        return type.code;
    }

    public static ResponseLogType get(Integer code) {
        ResponseLogType type = map.get(code);
        if (type == null) {
            return PART;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        ResponseLogType type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
