package io.geekidea.boot.common.enums;

/**
 * 系统类型
 *
 * @author geekidea
 * @date 2023/11/15
 **/
public enum FileServerType {

    LOCAL(1, "本地文件服务"),
    OSS(2, "OSS文件服务");

    private Integer code;
    private String desc;

    FileServerType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FileServerType getFileServerType(Integer code) {
        for (FileServerType fileServerType : values()) {
            if (fileServerType.getCode().equals(code)) {
                return fileServerType;
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
