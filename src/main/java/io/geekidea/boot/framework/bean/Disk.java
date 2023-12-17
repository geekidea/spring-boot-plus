package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 磁盘信息
 *
 * @author geekidea
 * @since 2023-12-17
 */
@Data
@Schema(description = "磁盘信息")
public class Disk implements Serializable {

    private static final long serialVersionUID = -5695612481481869927L;

    @Schema(description = "磁盘路径")
    private String mount;

    @Schema(description = "文件系统类型")
    private String type;

    @Schema(description = "磁盘名称")
    private String name;

    @Schema(description = "磁盘总大小")
    private long totalSpace;

    @Schema(description = "磁盘可用空间大小")
    private long usableSpace;

    @Schema(description = "磁盘已使用空间大小")
    private long usedSpace;

    @Schema(description = "磁盘总大小带单位")
    private String totalSpaceUnit;

    @Schema(description = "磁盘总大小带单位")
    private String usableSpaceUnit;

    @Schema(description = "磁盘总大小带单位")
    private String usedSpaceSpaceUnit;

    @Schema(description = "可用百分比")
    private BigDecimal usableRate;

    @Schema(description = "已用百分比")
    private BigDecimal usedRate;

}

