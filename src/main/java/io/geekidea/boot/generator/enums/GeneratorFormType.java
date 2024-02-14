package io.geekidea.boot.generator.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 表单类型 1：单行文本，2：多行文本，3：数字框，4：单选框，5：复选框，6：下拉框，7：日期，8：日期时间，9：时间，10：文件上传，11：富文本
 *
 * @author geekidea
 * @date 2023/12/31
 **/
public enum GeneratorFormType {

    INPUT(1, "单行文本"),
    TEXTAREA(2, "多行文本"),
    NUMBER(3, "数字框"),
    RADIO(4, "单选框"),
    CHECKBOX(5, "复选框"),
    SELECT(6, "下拉框"),
    DATE(7, "日期"),
    DATETIME(8, "日期时间"),
    TIME(9, "日期时间"),
    FILE(10, "文件上传"),
    RICH_TEXT(11, "富文本");

    private Integer code;

    private String desc;

    GeneratorFormType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, GeneratorFormType> map = new HashMap<>();

    static {
        for (GeneratorFormType type : values()) {
            map.put(type.code, type);
        }
    }

    public static Integer getCode(GeneratorFormType type) {
        if (type == null) {
            return INPUT.code;
        }
        return type.code;
    }

    public static GeneratorFormType get(Integer code) {
        GeneratorFormType type = map.get(code);
        if (type == null) {
            return INPUT;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        GeneratorFormType type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

}
