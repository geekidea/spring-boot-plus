package io.geekidea.springbootplus.foobar.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.geekidea.springbootplus.common.pagination.BasePageOrderParam;

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
@ApiModel(value = "FooBarPageParam对象", description = "FooBar查询参数")
// fooBarBasePageParamBaseOrder
public class FooBarPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
