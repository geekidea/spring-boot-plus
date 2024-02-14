package io.geekidea.boot.generator.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询方式 1：等于，2：不等于，3：大于，4：大于等于，5：小于，6：小于等于，7：like，8：日期范围，9：日期时间范围
 *
 * @author geekidea
 * @date 2023/12/31
 **/
public enum GeneratorQueryType {

    EQ(1, "等于"),
    NE(2, "不等于"),
    GT(3, "大于"),
    GE(4, "大于等于"),
    LT(5, "小于"),
    LE(6, "小于等于"),
    LIKE(7, "like"),
    DATE_RANGE(8, "日期范围"),
    DATETIME_RANGE(9, "日期时间范围");

    private Integer code;

    private String desc;

    GeneratorQueryType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, GeneratorQueryType> map = new HashMap<>();

    static {
        for (GeneratorQueryType type : values()) {
            map.put(type.code, type);
        }
    }

    public static Integer getCode(GeneratorQueryType type) {
        if (type == null) {
            return EQ.code;
        }
        return type.code;
    }

    public static GeneratorQueryType get(Integer code) {
        GeneratorQueryType type = map.get(code);
        if (type == null) {
            return EQ;
        }
        return type;
    }

    public static String getDesc(Integer code) {
        GeneratorQueryType type = get(code);
        if (type == null) {
            return null;
        }
        return type.getDesc();
    }

}
