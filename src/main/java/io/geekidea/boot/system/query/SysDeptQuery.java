package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageOrderQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门查询参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "部门查询参数")
public class SysDeptQuery extends BasePageOrderQuery {
    private static final long serialVersionUID = 1L;


}

