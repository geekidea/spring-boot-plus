package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 操作系统信息
 *
 * @author geekidea
 * @date 2023/12/17
 **/
@Data
@Schema(description = "操作系统信息")
public class OperatingSystemInfo implements Serializable {

    private static final long serialVersionUID = 8809514536353797869L;

    @Schema(description = "系统名称")
    private String name;

    @Schema(description = "系统架构")
    private String arch;

    @Schema(description = "系统版本")
    private String version;

    @Schema(description = "IP地址")
    private String address;
}
