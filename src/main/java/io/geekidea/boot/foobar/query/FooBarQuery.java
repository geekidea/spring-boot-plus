package io.geekidea.boot.foobar.query;

import io.geekidea.boot.framework.page.BasePageQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * FooBar查询参数
 *
 * @author geekidea
 * @since 2023-11-14
 */
@Data
@Schema(description = "FooBar查询参数")
public class FooBarQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

