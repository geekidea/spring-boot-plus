package io.geekidea.boot.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统类型
 *
 * @author geekidea
 * @date 2023/11/15
 **/
public enum FileServerType {

    LOCAL(1, "本地文件服务"),
    OSS(2, "OSS文件服务");

    private static final Map<Integer, FileServerType> map = new HashMap<>();

    static {
        for (FileServerType type : values()) {
            map.put(type.code, type);
        }
    }

    private Integer code;
    private String desc;

    FileServerType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Integer getCode(FileServerType type) {
        if (type == null) {
            return LOCAL.code;
        }
        return type.code;
    }

    public static FileServerType get(Integer code) {
        FileServerType type = map.get(code);
        if (type == null) {
            return LOCAL;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        FileServerType fileServerType = get(code);
        if (fileServerType == null) {
            return null;
        }
        return fileServerType.getDesc();
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
