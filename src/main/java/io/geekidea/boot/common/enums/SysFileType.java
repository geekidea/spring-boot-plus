package io.geekidea.boot.common.enums;

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

    public static SysFileType getSysFileType(Integer code) {
        for (SysFileType sysFileType : values()) {
            if (sysFileType.getCode().equals(code)) {
                return sysFileType;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
