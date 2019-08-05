package io.geekidea.springbootplus.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.geekidea.springbootplus.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * IP地址
 * </p>
 *
 * @author geekidea
 * @since 2019-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Ip对象", description="IP地址")
public class Ip extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ip开始地址")
    private String ipStart;

    @ApiModelProperty(value = "ip结束地址")
    private String ipEnd;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "运营商")
    private String operator;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "ip开始地址数字")
    private Long ipStartNum;

    @ApiModelProperty(value = "ip结束地址数字")
    private Long ipEndNum;

}
