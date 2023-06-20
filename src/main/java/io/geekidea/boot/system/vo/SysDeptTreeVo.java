package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author geekidea
 * @date 2023/6/18
 **/
@Data
@Schema(description = "部门树形列表VO")
public class SysDeptTreeVo {

    @Schema(description = "部门ID")
    private Long id;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "子部门集合")
    private List<SysDeptTreeVo> children;

}
