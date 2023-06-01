package io.geekidea.boot.framework.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可排序查询参数对象
 *
 * @author geekidea
 * @since 2019-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "排序分页查询参数")
public abstract class BasePageOrderQuery extends BasePageQuery {
    private static final long serialVersionUID = -2274379904047683719L;

    @Schema(description = "排序")
    private OrderByItem orderBy;

}
