package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置VO
 *
 * @author geekidea
 * @since 2023-11-27
 */
@Data
@Schema(description = "系统配置查询结果")
public class SysConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "配置名称")
    private String configName;

    @Schema(description = "配置key")
    private String configKey;

    @Schema(description = "配置值")
    private String configValue;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人ID")
    private Long createId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改人ID")
    private Long updateId;

    @Schema(description = "修改时间")
    private Date updateTime;

}

