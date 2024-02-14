package io.geekidea.boot.util;

import com.github.pagehelper.PageHelper;
import io.geekidea.boot.common.constant.CommonConstant;
import io.geekidea.boot.framework.page.BasePageQuery;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import org.apache.commons.lang3.StringUtils;

/**
 * @author geekidea
 * @date 2023/11/19
 **/
public class PagingUtil {

    /**
     * 处理分页
     *
     * @param basePageQuery
     */
    public static void handlePage(BasePageQuery basePageQuery) {
        handlePage(basePageQuery, null);
    }


    /**
     * 处理分页
     *
     * @param basePageQuery
     * @param defaultOrderBy
     */
    public static void handlePage(BasePageQuery basePageQuery, String defaultOrderBy) {
        handlePage(basePageQuery, null, defaultOrderBy);
    }

    public static void handlePage(BasePageQuery basePageQuery, OrderMapping orderMapping, String defaultOrderBy) {
        String orderBy = defaultOrderBy;
        Integer pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;
        Integer pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
        if (basePageQuery != null) {
            pageIndex = basePageQuery.getPageIndex();
            pageSize = basePageQuery.getPageSize();
            // 判断参数中是否有排序
            String orderByColumn = basePageQuery.getOrderByColumn();
            if (StringUtils.isNotBlank(orderByColumn)) {
                // 获取列映射
                if (orderMapping != null && orderMapping.size() > 0) {
                    String dbColumn = orderMapping.get(orderByColumn);
                    // 如果没找到对应的数据库映射列，则使用当前参数列名称
                    if (StringUtils.isNotBlank(dbColumn)) {
                        orderByColumn = dbColumn;
                    }
                }
                Boolean orderByAsc = basePageQuery.getOrderByAsc();
                String paramOrderBy = OrderByItem.orderBy(orderByColumn, orderByAsc);
                if (StringUtils.isNotBlank(paramOrderBy)) {
                    orderBy = paramOrderBy;
                }
            }
        }
        // 分页
        PageHelper.startPage(pageIndex, pageSize, orderBy);
    }

}
