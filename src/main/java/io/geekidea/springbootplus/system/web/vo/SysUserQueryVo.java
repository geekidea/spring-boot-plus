package io.geekidea.springbootplus.system.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * SystemUser 查询结果对象
 * </p>
 *
 * @author geekidea
 * @date 2019-08-26
 */
@Data
@ApiModel(value="SysUserQueryVo对象", description="SystemUser查询参数")
public class SysUserQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "account")
    private String account;

    @ApiModelProperty(value = "password")
    private String pwd;

    @ApiModelProperty(value = "remark")
    private String remark;

    @ApiModelProperty(value = "create time")
    private Date createTime;

    @ApiModelProperty(value = "update time")
    private Date updateTime;

}