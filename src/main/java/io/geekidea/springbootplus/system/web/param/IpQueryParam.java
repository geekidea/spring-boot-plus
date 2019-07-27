package io.geekidea.springbootplus.system.web.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import io.geekidea.springbootplus.common.web.param.QueryParam;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author geekidea
 * @date 2019-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="IpQueryParam对象", description="查询参数")
public class IpQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
