package io.geekidea.boot.generator.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置前端表单布局方式 1：一行一列，2：一行两列
 *
 * @author geekidea
 * @date 2024/1/24
 **/
public enum GeneratorFormLayout {

    ONE(1, "一行一列"),
    TWO(2, "一行两列");

    private Integer code;

    private String desc;

    GeneratorFormLayout(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, GeneratorFormLayout> map = new HashMap<>();

    static {
        for (GeneratorFormLayout type : values()) {
            map.put(type.code, type);
        }
    }

    public static Integer getCode(GeneratorFormLayout type) {
        if (type == null) {
            return TWO.code;
        }
        return type.code;
    }

    public static GeneratorFormLayout get(Integer code) {
        GeneratorFormLayout type = map.get(code);
        if (type == null) {
            return TWO;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        GeneratorFormLayout type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

}
