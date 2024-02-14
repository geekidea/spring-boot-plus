package io.geekidea.boot.framework.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author geekidea
 * @date 2022/4/12
 **/
@Data
@Schema(description = "分页排序")
public class OrderByItem {

    private static final String DESC = "desc";

    @Schema(description = "排序列名称")
    private String column;

    @Schema(description = "排序方式 true：升序，false：降序")
    private boolean asc = true;

    public static String orderBy(String column, Boolean asc) {
        if (asc == null) {
            asc = true;
        }
        if (asc) {
            return asc(column);
        } else {
            return desc(column);
        }
    }

    public static String asc(String column) {
        if (StringUtils.isBlank(column)) {
            return null;
        }
        return column;
    }

    public static String desc(String column) {
        if (StringUtils.isBlank(column)) {
            return null;
        }
        return column + " " + DESC;
    }

    public static String orderBy(String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            return null;
        }
        return orderBy;
    }

}
