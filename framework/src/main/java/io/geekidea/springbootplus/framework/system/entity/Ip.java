package io.geekidea.springbootplus.framework.system.entity;

import io.geekidea.springbootplus.framework.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.geekidea.springbootplus.framework.core.validator.groups.Update;

/**
 * IP地址
 *
 * @author geekidea
 * @since 2020-03-20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Ip对象")
public class Ip extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @NotBlank(message = "不能为空")
    private String ipStart;

    @NotBlank(message = "不能为空")
    private String ipEnd;

    @NotBlank(message = "不能为空")
    private String area;

    @NotBlank(message = "不能为空")
    private String operator;

    @NotNull(message = "不能为空")
    private Long ipStartNum;

    @NotNull(message = "不能为空")
    private Long ipEndNum;

}
