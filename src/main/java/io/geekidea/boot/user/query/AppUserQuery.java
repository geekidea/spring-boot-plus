package io.geekidea.boot.user.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * App用户信息查询参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App用户信息查询参数")
public class AppUserQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

