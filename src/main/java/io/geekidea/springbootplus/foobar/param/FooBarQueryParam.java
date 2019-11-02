package io.geekidea.springbootplus.foobar.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.geekidea.springbootplus.common.param.OrderQueryParam;

/**
 * <pre>
 * FooBar 查询参数对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-11-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FooBarQueryParam对象", description = "FooBar查询参数")
public class FooBarQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
}
