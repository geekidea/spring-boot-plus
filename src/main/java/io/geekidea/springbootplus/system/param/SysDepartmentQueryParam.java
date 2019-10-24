package io.geekidea.springbootplus.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.geekidea.springbootplus.common.param.OrderQueryParam;

/**
 * <pre>
 * 部门 查询参数对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysDepartmentQueryParam对象", description = "部门查询参数")
public class SysDepartmentQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
}
