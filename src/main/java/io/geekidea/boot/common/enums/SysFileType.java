package io.geekidea.boot.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型 1：图片，2：音视频，3：文档，4：文件
 *
 * @author geekidea
 * @date 2023/11/27
 **/
public enum SysFileType {

    IMAGE(1, "图片"),
    VIDEO(2, "音视频"),
    OFFICE(3, "文档"),
    FILE(4, "文件");

    private Integer code;
    private String desc;

    SysFileType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, SysFileType> map = new HashMap<>();

    static {
        for (SysFileType type : values()) {
            map.put(type.code, type);
        }
    }

    public static Integer getCode(SysFileType type) {
        if (type == null) {
            return null;
        }
        return type.code;
    }

    public static SysFileType get(Integer code) {
        SysFileType type = map.get(code);
        if (type == null) {
            return null;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        SysFileType type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

}
