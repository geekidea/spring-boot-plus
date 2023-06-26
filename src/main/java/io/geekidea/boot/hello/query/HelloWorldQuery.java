package io.geekidea.boot.hello.query;

import io.geekidea.boot.framework.page.BasePageQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 你好世界查询参数
 *
 * @author geekidea
 * @since 2023-06-26
 */
@Data
@Schema(description = "你好世界查询参数")
public class HelloWorldQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

