package io.geekidea.boot.framework.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import io.geekidea.boot.framework.constant.CommonConstant;
import io.geekidea.boot.framework.page.BasePageQuery;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.service.BaseService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author geekidea
 * @date 2022/3/16
 **/
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {


    /**
     * 处理分页
     *
     * @param basePageQuery
     */
    protected void handlePage(BasePageQuery basePageQuery) {
        handlePage(basePageQuery, null);
    }

    /**
     * 处理分页
     *
     * @param basePageQuery
     * @param defaultOrderBy
     */
    protected void handlePage(BasePageQuery basePageQuery, String defaultOrderBy) {
        String orderBy = defaultOrderBy;
        Integer pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;
        Integer pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
        if (basePageQuery != null) {
            pageIndex = basePageQuery.getPageIndex();
            pageSize = basePageQuery.getPageSize();
            // 判断参数中是否有排序
            OrderByItem orderByItem = basePageQuery.getOrderBy();
            if (orderByItem != null) {
                String paramOrderBy = orderByItem.getOrderBy();
                if (StringUtils.isNotBlank(paramOrderBy)) {
                    orderBy = paramOrderBy;
                }
            }
        }
        PageHelper.startPage(pageIndex, pageSize, orderBy);
    }

}
