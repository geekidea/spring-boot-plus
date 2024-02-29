package io.geekidea.boot.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统类型
 *
 * @author geekidea
 * @date 2023/11/15
 **/
public enum SystemType {

    ADMIN(1, "Admin管理后台"),
    APP(2, "APP移动端端");

    private static final Map<Integer, SystemType> map = new HashMap<>();

    static {
        for (SystemType type : values()) {
            map.put(type.code, type);
        }
    }

    private Integer code;
    private String desc;

    SystemType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Integer getCode(SystemType type) {
        if (type == null) {
            return null;
        }
        return type.code;
    }

    public static SystemType get(Integer code) {
        SystemType type = map.get(code);
        if (type == null) {
            return null;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        SystemType type = get(code);
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
