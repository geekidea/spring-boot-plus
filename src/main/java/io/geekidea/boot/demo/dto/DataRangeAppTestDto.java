package io.geekidea.boot.demo.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改用户端数据权限测试参数
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Data
@Schema(description = "修改用户端数据权限测试参数")
public class DataRangeAppTestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 20, message = "名称长度超过限制")
    private String name;

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "备注")
    @Length(max = 200, message = "备注长度超过限制")
    private String remark;

}


