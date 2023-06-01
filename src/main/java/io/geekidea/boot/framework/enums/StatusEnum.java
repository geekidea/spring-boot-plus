package io.geekidea.boot.framework.enums;

/**
 * @author geekidea
 * @date 2022/5/30
 **/
public enum StatusEnum {

    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatusEnum getStatus(Integer code) {
        if (code == null) {
            return DISABLE;
        }
        for (StatusEnum statusEnum : values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return DISABLE;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
