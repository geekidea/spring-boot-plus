package io.geekidea.boot.demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户端数据权限测试VO
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Data
@Schema(description = "用户端数据权限测试查询结果")
public class DataRangeAppTestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

