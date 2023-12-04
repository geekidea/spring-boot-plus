package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典类型查询参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "字典类型查询参数")
public class SysDictTypeQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

