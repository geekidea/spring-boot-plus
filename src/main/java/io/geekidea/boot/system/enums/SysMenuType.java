package io.geekidea.boot.system.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统菜单类型
 *
 * @author geekidea
 * @date 2024/1/21
 **/
public enum SysMenuType {

    DIR(1, "目录"),
    MENU(2, "菜单"),
    PERMISSION(3, "权限");

    private static final Map<Integer, SysMenuType> map = new HashMap<>();

    static {
        for (SysMenuType type : values()) {
            map.put(type.code, type);
        }
    }

    private Integer code;
    private String desc;

    SysMenuType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Integer getCode(SysMenuType type) {
        if (type == null) {
            return null;
        }
        return type.code;
    }

    public static SysMenuType get(Integer code) {
        SysMenuType type = map.get(code);
        if (type == null) {
            return null;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        SysMenuType type = get(code);
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
