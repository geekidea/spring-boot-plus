package io.geekidea.boot.demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试商品VO
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Data
@Schema(description = "测试商品查询结果")
public class DemoProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

