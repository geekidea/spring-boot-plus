package io.geekidea.springbootplus.system.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.time.LocalDateTime;

/**
 * <p>
 *  查询结果对象
 * </p>
 *
 * @author geekidea
 * @date 2019-07-27
 */
@Data
@ApiModel(value="IpQueryVo对象", description="查询参数")
public class IpQueryVo implements Serializable{

    private static final long serialVersionUID = 1L;

private String ipStart;

private String ipEnd;

private String area;

private String operator;

private Long id;

private Long ipStartNum;

private Long ipEndNum;


}