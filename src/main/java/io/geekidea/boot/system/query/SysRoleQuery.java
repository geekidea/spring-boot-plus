package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageOrderQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统角色查询参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "系统角色查询参数")
public class SysRoleQuery extends BasePageOrderQuery {
    private static final long serialVersionUID = 1L;


}

