package io.geekidea.boot.demo.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AppFooBar查询参数
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Data
@Schema(description = "AppFooBar查询参数")
public class FooBarAppQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

