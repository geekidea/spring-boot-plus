package io.geekidea.boot.generator.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成代码的模板类型 1：后台代码，2：前端代码，3：菜单SQL
 *
 * @author geekidea
 * @date 2024/1/22
 **/
public enum GeneratorTemplateType {

    BACKEND(1, "后台代码"),
    FRONTEND(2, "前端代码"),
    MENU(3, "菜单SQL");

    private Integer code;

    private String desc;

    GeneratorTemplateType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, GeneratorTemplateType> map = new HashMap<>();

    static {
        for (GeneratorTemplateType type : values()) {
            map.put(type.code, type);
        }
    }

    public static Integer getCode(GeneratorTemplateType type) {
        if (type == null) {
            return null;
        }
        return type.code;
    }

    public static GeneratorTemplateType get(Integer code) {
        GeneratorTemplateType type = map.get(code);
        if (type == null) {
            return null;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        GeneratorTemplateType type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

}
