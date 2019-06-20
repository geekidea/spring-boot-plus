package io.geekidea.springbootplus.system.entity;

import io.geekidea.springbootplus.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author geekidea
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Ip对象")
public class Ip extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String ipStart;

    private String ipEnd;

    private String area;

    private String operator;

    private Long id;

    private Long ipStartNum;

    private Long ipEndNum;

}
