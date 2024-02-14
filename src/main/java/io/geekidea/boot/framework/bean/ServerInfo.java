package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 服务信息
 *
 * @author geekidea
 * @since 2023-12-16
 */
@Data
@Schema(description = "服务信息")
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = 3621691988717747918L;

    @Schema(description = "CPU信息")
    private Cpu cpu;

    @Schema(description = "内存信息")
    private Memory memory;

    @Schema(description = "JVM信息")
    private Jvm jvm;

    @Schema(description = "磁盘列表")
    private List<Disk> diskList;

    @Schema(description = "操作系统信息")
    private OperatingSystemInfo operatingSystemInfo;

    @Schema(description = "项目信息")
    private ProjectInfo projectInfo;


}

