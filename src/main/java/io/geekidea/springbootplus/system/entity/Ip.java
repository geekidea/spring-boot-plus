package io.geekidea.springbootplus.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.geekidea.springbootplus.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * IP地址
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Ip对象", description = "IP地址")
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
