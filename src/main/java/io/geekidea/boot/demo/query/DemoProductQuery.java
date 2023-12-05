package io.geekidea.boot.demo.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 测试商品查询参数
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Data
@Schema(description = "测试商品查询参数")
public class DemoProductQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}
