package io.geekidea.boot.demo.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 演示查询参数
 *
 * @author geekidea
 * @since 2023-12-03
 */
@Data
@Schema(description = "演示查询参数")
public class DemoQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

