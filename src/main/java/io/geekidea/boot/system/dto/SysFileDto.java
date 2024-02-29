package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 修改系统文件参数
 *
 * @author geekidea
 * @since 2023-11-26
 */
@Data
@Schema(description = "系统文件参数")
public class SysFileDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "源文件名称")
    @Length(max = 200, message = "源文件名称长度超过限制")
    private String originalFileName;

}


