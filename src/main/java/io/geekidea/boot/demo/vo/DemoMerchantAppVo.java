package io.geekidea.boot.demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * App测试商户VO
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Data
@Schema(description = "App测试商户查询结果")
public class DemoMerchantAppVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "商户名称")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

