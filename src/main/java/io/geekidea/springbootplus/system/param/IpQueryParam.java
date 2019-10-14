package io.geekidea.springbootplus.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.geekidea.springbootplus.common.param.QueryParam;

/**
 * <p>
 * IP地址 查询参数对象
 * </p>
 *
 * @author geekidea
 * @date 2019-10-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IpQueryParam对象", description = "IP地址查询参数")
public class IpQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
