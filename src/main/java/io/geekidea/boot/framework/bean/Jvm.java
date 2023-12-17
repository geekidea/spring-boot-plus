package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JVM信息
 *
 * @author geekidea
 * @date 2023/12/16
 **/
@Data
@Schema(description = "JVM信息")
public class Jvm implements Serializable {

    private static final long serialVersionUID = 7303907109495309018L;

    @Schema(description = "JVM已分配内存")
    private long totalMemory;

    @Schema(description = "JVM最大内存")
    private long maxMemory;

    @Schema(description = "JVM最大可用内存")
    private long usableMemory;

    @Schema(description = "JVM空闲内存")
    private long freeMemory;

    @Schema(description = "JVM已分配内存存带单位")
    private String totalMemoryUnit;

    @Schema(description = "JVM最大内存带单位")
    private String maxMemoryUnit;

    @Schema(description = "JVM最大可用内存带单位")
    private String usableMemoryUnit;

    @Schema(description = "JVM空闲内存带单位")
    private String freeMemoryUnit;

    @Schema(description = "JVM名称")
    private String name;

    @Schema(description = "JVM厂商")
    private String vendor;

    @Schema(description = "java版本")
    private String version;

    @Schema(description = "java按照目录")
    private String home;

    @Schema(description = "进程ID")
    private int processId;

    @Schema(description = "服务启动的开始时间")
    private Date startTime;

    @Schema(description = "服务已运行的时间")
    private long userTime;

    @Schema(description = "服务已运行的时间描述")
    private String userTimeDesc;

    @Schema(description = "进程CPU使用率")
    private BigDecimal processCpuRate;

}
