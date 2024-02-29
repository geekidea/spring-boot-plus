package io.geekidea.boot.generator.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成方式 1：zip压缩包，2：自定义路径
 *
 * @author geekidea
 * @date 2024/1/24
 **/
public enum GeneratorType {

    ZIP(1, "zip压缩包"),
    CUSTOM(2, "自定义路径");

    private static final Map<Integer, GeneratorType> map = new HashMap<>();

    static {
        for (GeneratorType type : values()) {
            map.put(type.code, type);
        }
    }

    private Integer code;
    private String desc;

    GeneratorType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Integer getCode(GeneratorType type) {
        if (type == null) {
            return ZIP.code;
        }
        return type.code;
    }

    public static GeneratorType get(Integer code) {
        GeneratorType type = map.get(code);
        if (type == null) {
            return ZIP;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        GeneratorType type = get(code);
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
