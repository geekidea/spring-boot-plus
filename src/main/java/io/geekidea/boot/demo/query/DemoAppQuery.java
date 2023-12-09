package io.geekidea.boot.demo.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * App演示查询参数
 *
 * @author geekidea
 * @since 2023-12-09
 */
@Data
@Schema(description = "App演示查询参数")
public class DemoAppQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

