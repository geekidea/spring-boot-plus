package io.geekidea.springbootplus.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.geekidea.springbootplus.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author geekidea
 * @since 2019-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Ip对象", description="")
public class Ip extends BaseEntity {

    private static final long serialVersionUID = 1L;

private String ipStart;

private String ipEnd;

private String area;

private String operator;

    @TableId(value = "id", type = IdType.AUTO)
private Long id;

private Long ipStartNum;

private Long ipEndNum;

}
