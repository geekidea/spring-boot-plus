/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.geekidea.springbootplus.framework.common.entity.BaseEntity;
import io.geekidea.springbootplus.framework.core.validator.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 系统登录日志
 *
 * @author geekidea
 * @since 2020-03-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysLoginLog对象")
public class SysLoginLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("请求ID")
    private String requestId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("IP")
    private String ip;

    @ApiModelProperty("区域")
    private String area;

    @ApiModelProperty("运营商")
    private String operator;

    @ApiModelProperty("tokenMd5值")
    private String token;

    @ApiModelProperty("1:登录，2：登出")
    private Integer type;

    @ApiModelProperty("是否成功 true:成功/false:失败")
    private Boolean success;

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("失败消息记录")
    private String exceptionMessage;

    @ApiModelProperty("浏览器名称")
    private String userAgent;

    @ApiModelProperty("浏览器名称")
    private String browserName;

    @ApiModelProperty("浏览器版本")
    private String browserVersion;

    @ApiModelProperty("浏览器引擎名称")
    private String engineName;

    @ApiModelProperty("浏览器引擎版本")
    private String engineVersion;

    @ApiModelProperty("系统名称")
    private String osName;

    @ApiModelProperty("平台名称")
    private String platformName;

    @ApiModelProperty("是否是手机,0:否,1:是")
    private Boolean mobile;

    @ApiModelProperty("移动端设备名称")
    private String deviceName;

    @ApiModelProperty("移动端设备型号")
    private String deviceModel;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

}
