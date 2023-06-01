package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageOrderQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统登录日志查询参数
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Data
@Schema(description = "系统登录日志查询参数")
public class SysLoginLogQuery extends BasePageOrderQuery {
    private static final long serialVersionUID = 1L;


}

