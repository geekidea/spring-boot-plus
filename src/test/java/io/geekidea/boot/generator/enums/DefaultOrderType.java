package io.geekidea.boot.generator.enums;

/**
 * @author geekidea
 * @date 2022/7/3
 **/
public enum DefaultOrderType {

    PK_ID("pkId", "主键ID降序"),
    CREATE_TIME("createTime", "创建时间降序"),
    NONE("none", "无");

    private final String type;
    private final String desc;

    private DefaultOrderType(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static DefaultOrderType getDefaultOrderType(String type) {
        for (DefaultOrderType defaultOrderType : values()) {
            if (defaultOrderType.type.equalsIgnoreCase(type)) {
                return defaultOrderType;
            }
        }
        return NONE;
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }


}
