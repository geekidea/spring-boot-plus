package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 内存信息
 *
 * @author geekidea
 * @date 2023/12/16
 **/
@Data
@Schema(description = "内存信息")
public class Memory implements Serializable {

    private static final long serialVersionUID = -7825560455963508475L;

    @Schema(description = "总内存大小")
    private long total;

    @Schema(description = "空闲内存大小")
    private long available;

    @Schema(description = "已使用内存大小")
    private long used;

    @Schema(description = "总内存大小带单位")
    private String totalUnit;

    @Schema(description = "空闲内存大小带单位")
    private String availableUnit;

    @Schema(description = "已使用内存大小带单位")
    private String usedUnit;

    @Schema(description = "使用率")
    private BigDecimal usedRate;

    @Schema(description = "空闲率")
    private BigDecimal availableRate;

}
